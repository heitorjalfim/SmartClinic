package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.dto.PainelDTO;
import br.com.meets.cesar.praesens.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

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

    @PostMapping("/admin/agendar")
        public AgendamentoModel agendarPelaClinica(@RequestBody AgendamentoInputDTO dados) {
            return agendamentoService.salvar(dados, true);
        }

    @GetMapping("/disponibilidade")
    public ResponseEntity<List<PainelDTO>> consultarDisponibilidade(
            @RequestParam("data") String data) {
        LocalDate dataConsulta = LocalDate.parse(data);
        return ResponseEntity.ok(agendamentoService.consultarDisponibilidade(dataConsulta));
    }
}
