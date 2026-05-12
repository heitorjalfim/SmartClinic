package br.com.meets.cesar.praesens.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreOutputDTO {
    private String nivelRisco;
    private String recomendacao; 
    private String justificativa;
}