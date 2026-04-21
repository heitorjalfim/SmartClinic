package br.com.meets.cesar.praesens.dto;

import java.time.LocalDate;
import java.time.LocalTime; 

public record AgendamentoInputDTO(
    Long pacienteId,
    LocalDate data,
    LocalTime hora,
    Integer leadTime,
    String tipoProcedimento,
    Double valorProcedimento
) {}
