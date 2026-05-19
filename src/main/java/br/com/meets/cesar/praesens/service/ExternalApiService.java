package br.com.meets.cesar.praesens.service;

import java.time.*;
import lombok.Data;
import java.util.*;
import org.springframework.stereotype.Service;

@Data
@Service
public class ExternalApiService {
    public float getClima(String local, LocalTime hora, LocalDate data){
        return new Random().nextFloat();
    }

    public float getTransito(String local, LocalTime hora, LocalDate data){
        return new Random().nextFloat();
    }
}
