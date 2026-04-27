package br.com.meets.cesar.praesens.controller;

import br.com.meets.cesar.praesens.dto.AgendamentoInputDTO;
import br.com.meets.cesar.praesens.dto.ScoreOutputDTO;
import br.com.meets.cesar.praesens.service.PrevisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/previsao")
public class PrevisaoController {

    @Autowired
    private PrevisaoService previsaoService;

    @PostMapping("/analisar")
    public ResponseEntity<ScoreOutputDTO> analisar(@RequestBody AgendamentoInputDTO dados) {
        ScoreOutputDTO resultado = previsaoService.calcularRisco(dados);
        return ResponseEntity.ok(resultado);
    }

    //colocar endpoints pra api de clima e transito
    //colocar endpoints pra ia

    //TEM QUE VERIFICAR SE A API DE CLIMAS VAI ESTAR OF
    //VERIFICAR MASSA DE AGENDAMENTOS
    //EVITAR QUE TENHA UM OVERBOOKING
}