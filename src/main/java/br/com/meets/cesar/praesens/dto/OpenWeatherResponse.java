package br.com.meets.cesar.praesens.dto;

import java.util.List;
import lombok.Data;

@Data
public class OpenWeatherResponse {
    private List<WeatherDescription> weather;

    @Data
    public static class WeatherDescription {
        private String main; 
        private String description;
    }
}