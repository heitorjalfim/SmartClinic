package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.model.Agendamento;
import br.com.meets.cesar.praesens.model.Paciente;
import br.com.meets.cesar.praesens.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping("/agendar")
    public String agendar(@RequestParam String cpf, @RequestParam String nome, @RequestParam int leadTime, @RequestParam String tipo, @RequestParam Double valor) {
        return service.realizarAgendamento(cpf, nome,leadTime,tipo,valor);
    }

    @GetMapping("/registrar-falta")
    public String falta(@RequestParam String cpf) {
        return service.registrarFalta(cpf);
    }

    @GetMapping("/lista-agendamentos")
    public List<Agendamento> listarTodos() {
        return service.listarTodosAgendamentos();
    }

    //mostra a lista de risco
    @GetMapping("/painel")
    public List<Paciente> verPainel() {
        return service.obterListaPorRisco();
    }
}
