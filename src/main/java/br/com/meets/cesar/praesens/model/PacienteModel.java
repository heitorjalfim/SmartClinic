package br.com.meets.cesar.praesens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;



@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Paciente;
    
    @Email
   @JsonIgnore @Setter private String email;

    //criar um tipo pra cpf
   @JsonIgnore @Setter private String cpf;
  @JsonIgnore  @Setter private String nome;
    @Setter private int Historico_NoShow;
    @Setter private int Score_Honra;
}