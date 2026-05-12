package br.com.meets.cesar.praesens.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.time.*;
import java.util.*;

@Data
@AllArgsConstructor
public class PainelDTO {
    private LocalDate data;
    private List<LocalTime> horarioLivres;
}
