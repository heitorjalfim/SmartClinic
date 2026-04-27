package br.com.meets.cesar.praesens.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    // ferramenta para chamadas HTTP
    private final RestTemplate restTemplate = new RestTemplate();

    public String getClima(String localidade) {
        try {
            // simulando a chamada. depois trocaremos pela url real da api
            // string url = "https://api.weather.com/v1/..."
            return "Chuva Moderada"; 
        } catch (Exception e) {
            return "Serviço de Clima Indisponível";
        }
    }

    public String getTransito(String localidade) {
        try {
            // simulando a chamada
            return "Trânsito Intenso";
        } catch (Exception e) {
            return "Serviço de Trânsito Indisponível";
        }
    }
}