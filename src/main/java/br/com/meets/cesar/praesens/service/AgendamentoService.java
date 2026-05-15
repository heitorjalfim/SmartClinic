package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.PainelDTO;
import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.model.PacienteModel;
import br.com.meets.cesar.praesens.repository.AgendamentoRepository;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public AgendamentoModel salvar(AgendamentoInputDTO agendamento, boolean isOperacaoClinica) {
        Long totalMesmoHorario = agendamentoRepository.countByHoraAndData(agendamento.getHora(), agendamento.getData());
        int limite = isOperacaoClinica ? 2 : 1;
        if (totalMesmoHorario >= limite) {
            throw new RuntimeException("Limite de consultas atingido");
        }
        PacienteModel paciente = pacienteRepository.findByEmail(agendamento.getEMail()).orElseGet(() -> {
            PacienteModel pacienteNovo = new PacienteModel();
            pacienteNovo.setEmail(agendamento.getEMail());
            pacienteNovo.setNome(agendamento.getNomePaciente());
            pacienteNovo.setTelefone(agendamento.getTelefone());
            pacienteNovo.setHistorico_NoShow(0);
            pacienteNovo.setScore_Honra(0);
            pacienteNovo.setTotalAgendamentos(1);
            return pacienteRepository.save(pacienteNovo);
        });

        AgendamentoModel agendamentoNovo = new AgendamentoModel();
        agendamentoNovo.setData(agendamento.getData());
        agendamentoNovo.setHora(agendamento.getHora());
        agendamentoNovo.setLocalidade(agendamento.getLocalidade());
        agendamentoNovo.setPaciente(paciente);
        agendamentoNovo.setStatus("AGENDADO");
        agendamentoNovo.setProbabilidade_Falta(0.0);
        agendamentoNovo.setValor_Procedimento(agendamento.getValor_procedimento());
        agendamentoNovo.setTipo_Procedimento(agendamento.getTipo_procedimento());
        return agendamentoRepository.save(agendamentoNovo);
    }

    public void registrarFalta(Long idAgendamento) {
        agendamentoRepository.findById(idAgendamento).ifPresent(agendamento -> {
            PacienteModel paciente = agendamento.getPaciente();

            paciente.setHistorico_NoShow(paciente.getHistorico_NoShow() + 1);
         
            int honraAtual = paciente.getScore_Honra();
            if (honraAtual >= 10) {
                paciente.setScore_Honra(honraAtual - 10);
            } else {
                paciente.setScore_Honra(0);
            }

            pacienteRepository.save(paciente);
        });
    }

    public List<PainelDTO> consultarDisponibilidade(LocalDate data){
        List<PainelDTO> cronograma = new ArrayList<>();

        List<LocalTime> gradeFixa = List.of(
            LocalTime.of(8, 0), 
            LocalTime.of(9, 0), 
            LocalTime.of(10, 0),
            LocalTime.of(11, 0),
            LocalTime.of(14, 0),
            LocalTime.of(15, 0),   
            LocalTime.of(16, 0),  
            LocalTime.of(17, 0)   
        );

        List<AgendamentoModel> agendamentos = agendamentoRepository.findByData(data);
        List<LocalTime> horasOcupadas = agendamentos.stream().map(agenda -> agenda.getHora()).toList();
        List<LocalTime> horasLivres = gradeFixa.stream().filter(horario -> !horasOcupadas.contains(horario)).toList();

        cronograma.add(new PainelDTO(data, horasLivres));
        return cronograma;
    }

    public List<AgendamentoModel> listarTodos() {
        return agendamentoRepository.findAll();
    }
}