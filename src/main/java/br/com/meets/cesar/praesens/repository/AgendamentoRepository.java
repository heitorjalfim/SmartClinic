package br.com.meets.cesar.praesens.repository;

import br.com.meets.cesar.praesens.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

}