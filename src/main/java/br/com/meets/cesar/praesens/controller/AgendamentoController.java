package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping("/admin/agendar")
    public ResponseEntity<AgendamentoModel> agendarPelaClinica(@RequestBody AgendamentoInputDTO dados) {
        AgendamentoModel salvo = agendamentoService.salvar(dados, false);
        return ResponseEntity.status(201).body(salvo);
    }

    @PostMapping
    public ResponseEntity<AgendamentoModel> criar(@RequestBody AgendamentoInputDTO agendamento) {
        AgendamentoModel salvo = agendamentoService.salvar(agendamento, false);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoModel>> listar() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @PatchMapping("/{id}/falta")
    public ResponseEntity<Void> registrarFalta(@PathVariable Long id) {
        agendamentoService.registrarFalta(id);
        return ResponseEntity.noContent().build();
    }
}