package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.model.PacienteModel;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrevisaoService {

    @Autowired
    private IAService iaService;

    @Autowired
    private PacienteRepository pacienteRepository;

    public ScoreOutputDTO calcularRisco(AgendamentoInputDTO dto) {

        PacienteModel paciente = pacienteRepository.findByCpf(dto.getCpfPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));

        AgendamentoModel modelParaIA = new AgendamentoModel();
        
        modelParaIA.setPaciente(paciente);
        modelParaIA.setDataHora(dto.getDataHora()); 
        modelParaIA.setValor_Procedimento(dto.getValor_procedimento().doubleValue());
        modelParaIA.setTipo_Procedimento(dto.getTipo_procedimento());

        modelParaIA.setDistancia(0); 

        double probabilidade = iaService.preverRisco(modelParaIA);

        modelParaIA.setProbabilidade_Falta(probabilidade);

        ScoreOutputDTO resultado = new ScoreOutputDTO();
        resultado.setProbabilidadeFalta(probabilidade * 100);
        resultado.setNivelRisco(definirStatusRisco(probabilidade));
        
        return resultado;
    }

    private String definirStatusRisco(double p) {
        if (p > 0.8) return "CRÍTICO";
        if (p > 0.5) return "ALTO";
        if (p > 0.2) return "MÉDIO";
        return "BAIXO";
    }
}