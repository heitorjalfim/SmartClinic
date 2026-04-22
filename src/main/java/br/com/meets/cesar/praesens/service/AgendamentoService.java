package br.com.meets.cesar.praesens.service;

import br.com.meets.cesar.praesens.model.Agendamento;
import br.com.meets.cesar.praesens.model.Paciente;
import br.com.meets.cesar.praesens.repository.AgendamentoRepository;
import br.com.meets.cesar.praesens.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private PacienteRepository repository;
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public String realizarAgendamento(String cpf, String nome, int leadTime, String tipo, double valor) {
        //salva no repositorio de pacientes
        Paciente paciente = repository.findByCpf(cpf)
            .orElseGet(() -> {
                Paciente novo = new Paciente(null, cpf, nome, 0, 0, 0);
                novo.setScoreHonra(0); //ter certeza que o scoreHonra não é null *estava dando erro
                return repository.save(novo);
            });
        paciente.setTotalAgendamentos(paciente.getTotalAgendamentos() + 1);
        repository.save(paciente); // Salva ou Atualiza automaticamente
        
        //salva no repositorio de agendamentos
        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(paciente);
        agendamento.setLeadTime(leadTime);
        agendamento.setTipoProcedimento(tipo);
        agendamento.setValorProcedimento(valor);
        agendamento.setScoreRisco(paciente.getScoreRisco());

        agendamentoRepository.save(agendamento); // Salva ou Atualiza automaticamente

        return "Agendado! | Procedimento: "+tipo+" | Valor: R$ "+valor+" | Risco: "+ String.format("%.2f", paciente.getScoreRisco()) + "%";
    }

    public String registrarFalta(String cpf) {
        return repository.findByCpf(cpf).map(paciente -> {
            paciente.setTotalFaltas(paciente.getTotalFaltas() + 1);
            repository.save(paciente);
            return "Falta registrada para " + paciente.getNome() + ". Novo risco: " 
                    + String.format("%.2f", paciente.getScoreRisco()) + "%";
        }).orElse("Paciente não encontrado.");
    }

    public List<Agendamento> listarTodosAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public List<Paciente> obterListaPorRisco() {
        return repository.findAll(); // O banco retorna a lista atualizada
    }
}