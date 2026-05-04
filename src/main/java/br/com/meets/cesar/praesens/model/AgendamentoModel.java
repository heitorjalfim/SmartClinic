package br.com.meets.cesar.praesens.model;

import lombok.*;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Agendamento;

    @ManyToOne
    @Setter private PacienteModel paciente;

    @Column(nullable = true)
    @Setter private LocalDateTime dataHora = LocalDateTime.now();

    @Column(nullable = true)
    @Setter private Integer distancia = 0;

    @Setter private String Tipo_Procedimento;
    @Setter private Double Valor_Procedimento;
    @Setter private String status;

    @Column(nullable = true)
    @Setter private Double Probabilidade_Falta = 0.0;
}