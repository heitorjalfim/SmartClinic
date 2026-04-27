package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.model.PacienteModel;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrevisaoService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ExternalApiService externalApiService; // Para Clima e Trânsito

    @Autowired
    private GeminiService geminiService; // Para a análise do Gemini

    public ScoreOutputDTO calcularRisco(AgendamentoInputDTO dados) {
        
        // 1. Busca o paciente no banco pelo CPF com tratamento de erro (Melhoria de Robustez)
        PacienteModel paciente = pacienteRepository.findByCpf(dados.getCpfPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o CPF: " + dados.getCpfPaciente()));

        // 2. Consulta as APIs externas
        String condicaoClimatica = externalApiService.getClima(dados.getLocalidade());
        String condicaoTransito = externalApiService.getTransito(dados.getLocalidade());

        // 3. Verifica se alguma API está offline (Pedido do monitor)
        boolean apiOffline = condicaoClimatica.contains("Indisponível") || 
                             condicaoTransito.contains("Indisponível");

        // 4. Chama o Gemini enviando todos os contextos coletados
        ScoreOutputDTO resultadoFinal = geminiService.analisarRisco(
            paciente, 
            dados, 
            condicaoClimatica, 
            condicaoTransito
        );

        // 5. Adiciona o alerta visual na justificativa se as APIs falharam
        if (apiOffline) {
            String aviso = "[ALERTA: Fontes externas offline] ";
            resultadoFinal.setJustificativa(aviso + resultadoFinal.getJustificativa());
        }

        // 6. Garante que o DTO de saída mostre ao usuário qual era a condição climática usada
        resultadoFinal.setCondicaoClimatica(condicaoClimatica);

        return resultadoFinal;
    }
}