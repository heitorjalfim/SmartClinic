package br.com.meets.cesar.praesens.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Define como tabela SQL
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String nome;
    private int totalAgendamentos;
    private int totalFaltas;

    @Column (name = "score_honra")
    private Integer scoreHonra = 0;

    //getters e setters
    public int getScoreHonra(){
        return scoreHonra == null ? 0 : scoreHonra;
    } //ter certeza que o ScoreHonra não é null *isso tava dando erro

    public void setScoreHonra(int scoreHonra){
        this.scoreHonra = scoreHonra;
    }

    public double getScoreRisco() {
        if (totalAgendamentos == 1 && totalFaltas == 0) return 10.0; //taxa de falta para novos clientes
        return Math.min(((double) totalFaltas / totalAgendamentos) * 100, 100.0);
    }
}