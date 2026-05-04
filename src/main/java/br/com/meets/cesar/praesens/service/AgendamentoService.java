package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.model.PacienteModel;
import br.com.meets.cesar.praesens.repository.AgendamentoRepository;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private static final int LIMITE_POR_HORARIO = 5;

    public AgendamentoModel salvar(AgendamentoModel agendamento) {
        long totalNoMesmoHorario = agendamentoRepository.countByDataHora(agendamento.getDataHora());
        
        if (totalNoMesmoHorario >= LIMITE_POR_HORARIO) {
            throw new RuntimeException("Overbooking detectado: Limite de " + LIMITE_POR_HORARIO + " pacientes atingido para este horário.");
        }

        PacienteModel pacienteEnviado = agendamento.getPaciente();

        PacienteModel pacienteFinal = pacienteRepository.findByCpf(pacienteEnviado.getCpf())
            .orElseGet(() -> {

                PacienteModel novo = new PacienteModel();
                novo.setNome(pacienteEnviado.getNome());
                novo.setCpf(pacienteEnviado.getCpf());
                novo.setTotalAgendamentos(pacienteEnviado.getTotalAgendamentos() + 1);

                return pacienteRepository.save(novo);
            });

        agendamento.setPaciente(pacienteFinal);
        return agendamentoRepository.save(agendamento);
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

    public List<AgendamentoModel> listarTodos() {
        return agendamentoRepository.findAll();
    }
}