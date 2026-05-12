package br.com.meets.cesar.praesens.repository;

import br.com.meets.cesar.praesens.model.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;


public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long>{
    long countByHoraAndData(java.time.LocalTime Hora, java.time.LocalDate data);
    List<AgendamentoModel> findByData(LocalDate data);
}   
