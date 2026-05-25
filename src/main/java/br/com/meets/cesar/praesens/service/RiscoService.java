package br.com.meets.cesar.praesens.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.model.PacienteModel;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RiscoService {
    @Autowired
    private ExternalApiService apiService;

    @Value("${risco.peso.clima:0.3}")
    private double pesoClima;

    @Value("${risco.peso.transito:0.3}")
    private double pesoTransito;

    @Value("${risco.peso.noShow.alto:0.4}")
    private double pesoNoShowAlto;

    @Value("${risco.peso.noShow.medio:0.2}")
    private double pesoNoShowMedio;

    public ScoreOutputDTO analisarRisco(AgendamentoModel agendamento){
        String local = agendamento.getLocalidade();
        PacienteModel paciente = agendamento.getPaciente();
        ScoreOutputDTO score = new ScoreOutputDTO();

        float clima = apiService.getClima(local, agendamento.getHora(), agendamento.getData());

        StringBuilder justificativa = new StringBuilder();
        double pontosRisco = 0;
        double valorProcedimento = agendamento.getValor_Procedimento() != null ? agendamento.getValor_Procedimento() : 0.0;

        if(clima >= 0.7){
            justificativa.append("Condições climáticas desfavoráveis (chuva). ");
            pontosRisco += pesoClima;
        }

        if(paciente.getHistorico_NoShow() > 0 && paciente.getTotalAgendamentos() > 0 &&
           ((double) paciente.getHistorico_NoShow() / paciente.getTotalAgendamentos()) >= 0.5 ){
            justificativa.append("Paciente com alto histórico de faltas. ");
            pontosRisco += pesoNoShowAlto;
        }
        else if(paciente.getHistorico_NoShow() > 0){
            justificativa.append("Paciente possui faltas anteriores. ");
            pontosRisco += pesoNoShowMedio;
        }
        
        if (valorProcedimento > 1500.0) {
            justificativa.append("Procedimento de alto valor. ");
        }

        String nivelRisco;
        String recomendacao;

        if(pontosRisco >= 0.7){
            nivelRisco = "ALTO";
            recomendacao = "Ligar para o paciente para confirmar presença assim que possível";
        }
        else if(pontosRisco >= 0.3 || valorProcedimento > 1500.0){
            nivelRisco = "MEDIO";
            recomendacao = "Realizar confirmação da consulta via whatsapp";
        }
        else{
            nivelRisco = "BAIXO";
            recomendacao = "Acompanhar via lista de presença normal.";            
        }


        if(justificativa.length() == 0){
            justificativa.append("Nenhum cenario de risco detectado. ");
        } 

        score.setJustificativa(justificativa.toString().trim());
        score.setRecomendacao(recomendacao);
        score.setNivelRisco(nivelRisco);
        score.setDetalhesPesos(String.format(
            "Pesos usados -> clima: %.2f, transito: %.2f, noShowAlto: %.2f, noShowMedio: %.2f",
            pesoClima, pesoTransito, pesoNoShowAlto, pesoNoShowMedio
        ));
        return score;
    }
}
