package br.com.meets.cesar.praesens.model;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long id;

    @ManyToOne
    @Setter private PacienteModel paciente;

    @Column(nullable = true)
    @Setter private LocalTime hora;
    @Setter private LocalDate data;

    @Setter private String localidade;
    @Setter private String Tipo_Procedimento;
    @Setter private Double Valor_Procedimento;
    @Setter private String status;

    @Column(nullable = true)
    @Setter private Double Probabilidade_Falta = 0.0;

    @Column(name = "lead_time", nullable = false)
    @Setter private Integer leadTime;
}