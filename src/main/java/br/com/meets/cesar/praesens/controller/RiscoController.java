package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.service.*;
import br.com.meets.cesar.praesens.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risco")
public class RiscoController {

    @Autowired
    private RiscoService riscoService;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ScoreOutputDTO> consultarRisco(@PathVariable Long id) {
        return agendamentoRepository.findById(id).map(agendamento -> {
                    ScoreOutputDTO score = riscoService.analisarRisco(agendamento);
                    return ResponseEntity.ok(score);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}