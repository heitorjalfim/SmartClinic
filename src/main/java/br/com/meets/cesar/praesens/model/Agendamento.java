package br.com.meets.cesar.praesens.model;

import jakarta.persistence.*; 
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity 
@Table(name = "agendamentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Isso faz o PostgreSQL gerar o ID automaticamente
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime dataHora;
    
    private LocalTime hora; //horário do momento do agendamento
    private int leadTime; //Distância até o horário agendado
    private String tipoProcedimento; // Estético, Cirúrguco ou Manutenção
    private Double valorProcedimento;
    private double scoreRisco;

    //getters e setters

    public int getLeadTime(){
        return leadTime;
    }
    public void setLeadTime(int leadTime){
        this.leadTime = leadTime;
    }
    
    public String setTipoProcedimento(){
        return tipoProcedimento;
    }
    public void getTipoProcedimento(String tipoProcedimento){
        this.tipoProcedimento = tipoProcedimento;
    }

    public double getValorProcedimento(){
        return valorProcedimento;
    }
    public void setValorProcedimento(double valorProcedimento){
        this.valorProcedimento = valorProcedimento;
    }
}