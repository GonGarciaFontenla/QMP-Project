package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServicioMeteorologicoTest {

  private AccuWeatherAPI apiMock;
  private ServicioMeteorologico servicio;
  private final String direccion = "Buenos Aires, Argentina";

  @BeforeEach
  public void setUp() {
    apiMock = mock(AccuWeatherAPI.class);

    HashMap<String, Object> clima = new HashMap<>();
    clima.put("PrecipitationProbability", 0.5);
    List<HashMap<String, Object>> respuesta = Collections.singletonList(clima);

    when(apiMock.getWeather(direccion)).thenReturn(respuesta);

    servicio = new ServicioMeteorologico(apiMock, Duration.ofHours(3), direccion);
  }

  @Test
  public void testConsultaInicialLlamaAPI() {
    Map<String, Object> condiciones = servicio.obtenerCondicionesClimaticas();
    assertEquals(0.5, condiciones.get("PrecipitationProbability"));
    verify(apiMock, times(1)).getWeather(direccion);
  }

  @Test
  public void testLlamadoNoSeRepiteAntesDeExpirar() {
    servicio.obtenerCondicionesClimaticas();
    servicio.obtenerCondicionesClimaticas();
    verify(apiMock, times(1)).getWeather(direccion);
  }

  @Test
  public void testConsultaDespuesDeExpirarLlamaAPIOtraVez() throws NoSuchFieldException, IllegalAccessException {
    servicio.obtenerCondicionesClimaticas();
    var campo = servicio.getClass().getDeclaredField("proximaExpiracion");
    campo.setAccessible(true);
    campo.set(servicio, LocalDateTime.now().minusMinutes(1));

    servicio.obtenerCondicionesClimaticas();

    verify(apiMock, times(2)).getWeather(direccion);
  }
}
