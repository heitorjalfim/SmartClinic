package br.com.meets.cesar.praesens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OpenWeatherResponse {

    private MainData main;
    private List<WeatherDescription> weather;

    public MainData getMain() { return main; }
    public void setMain(MainData main) { this.main = main; }

    public List<WeatherDescription> getWeather() { return weather; }
    public void setWeather(List<WeatherDescription> weather) { this.weather = weather; }

    public static class MainData {
        private double temp;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }
    }

    public static class WeatherDescription {
        private String main; 
        private String description;

        public String getMain() { return main; }
        public void setMain(String main) { this.main = main; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}