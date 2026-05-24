package br.com.meets.cesar.praesens.dto;

import java.time.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AgendamentoInputDTO {
    @Email
    private String eMail;   
    private String nomePaciente;
    private String cpf;
    private String localidade;    
    private LocalTime hora;
    private LocalDate data;
    private Double valor_procedimento;
    private String tipo_procedimento;
    private String telefone;
}