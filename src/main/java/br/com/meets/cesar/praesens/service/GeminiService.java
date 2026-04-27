package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.model.PacienteModel;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    public ScoreOutputDTO analisarRisco(PacienteModel paciente, AgendamentoInputDTO dados, String clima, String transito) {
        ScoreOutputDTO output = new ScoreOutputDTO();

        try {
            // AQUI entrará a chamada HTTP para o Google Gemini quando você tiver a Key
            // Por enquanto, vamos simular que a IA processou os dados:
            
            if (clima.equals("Chuvoso") || transito.contains("Lento")) {
                output.setProbabilidadeFalta(65.0);
                output.setJustificativa("Risco aumentado devido a condições externas desfavoráveis e histórico do paciente.");
                output.setRecomendacao("Enviar lembrete reforçado 2 horas antes.");
            } else {
                output.setProbabilidadeFalta(10.0);
                output.setJustificativa("Condições favoráveis e bom histórico de honra.");
                output.setRecomendacao("Seguir com agendamento padrão.");
            }

        } catch (Exception e) {
            output.setProbabilidadeFalta(50.0);
            output.setJustificativa("IA Temporariamente offline. Cálculo baseado em média histórica.");
        }

        return output;
    }
}