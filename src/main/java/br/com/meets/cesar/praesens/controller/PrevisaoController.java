package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.repository.*;
import br.com.meets.cesar.praesens.model.AgendamentoModel;
import br.com.meets.cesar.praesens.service.PrevisaoService;
import br.com.meets.cesar.praesens.service.IAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/previsao")
public class PrevisaoController {

    @Autowired
    private PrevisaoService previsaoService;
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private IAService iaService;

    @PostMapping("/analisar")
    public ResponseEntity<ScoreOutputDTO> analisar(@RequestBody AgendamentoInputDTO dados) {
        ScoreOutputDTO resultado = previsaoService.calcularRisco(dados);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/treinar")
    public String treinar() {
        
        List<AgendamentoModel> todos = agendamentoRepository.findAll();
        if(todos.isEmpty()) return "Sem dados para treinar!";
        iaService.treinarModeloManual(todos);
        return "IA Treinada com sucesso!";
    }

    //TEM QUE VERIFICAR SE A API DE CLIMAS VAI ESTAR OF
    //VERIFICAR MASSA DE AGENDAMENTOS
    //EVITAR QUE TENHA UM OVERBOOKING
}