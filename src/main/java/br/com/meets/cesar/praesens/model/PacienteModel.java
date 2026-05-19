package br.com.meets.cesar.praesens.model;

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
    @Column(name = "id_paciente")
    private Long id;

    @Email
    @Column(nullable = true)
    @Setter private String email;

    @Setter private String nome;

    @Column(name = "historico_no_show", nullable = false)
    @Setter private Integer Historico_NoShow = 0;

    @Column(name = "total_agendamentos", nullable = false)
    @Setter private Integer totalAgendamentos = 0;

    @Column(name = "score_honra", nullable = false)
    @Setter private Integer Score_Honra = 100;

    @Setter private String telefone;
}