package org.example;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

public class ServicioMeteorologico {

  private Map<String, Object> ultimaRespuesta;
  private LocalDateTime proximaExpiracion;
  private final AccuWeatherAPI api;
  private final Duration expiracion;
  private final String direccion;

  public ServicioMeteorologico(AccuWeatherAPI api, Duration periodoValidez, String direccion) {
    this.api = api;
    this.expiracion = periodoValidez;
    this.direccion = direccion;
  }

  public Map<String, Object> obtenerCondicionesClimaticas() {
    if (this.ultimaRespuesta == null || expiro()) {
      this.ultimaRespuesta = consultarApi();
      this.proximaExpiracion = LocalDateTime.now().plus(this.expiracion);
    }
    return this.ultimaRespuesta;
  }

  private Map<String, Object> consultarApi() {
    List<HashMap<String, Object>> condiciones = api.getWeather(direccion);
    return condiciones.get(0);
  }

  private boolean expiro() {
    return LocalDateTime.now().isAfter(this.proximaExpiracion);
  }
}
