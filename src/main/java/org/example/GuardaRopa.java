package org.example;

import org.example.Engine.SuggestionEngine;
import org.example.Prenda.Prenda;
import org.example.Propuestas.PropuestaModificacionGuardaRopa;

import java.util.List;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuardaRopa {
  private final String id;
  private final List<Prenda> prendas;
  private final List<String> idUsuarios;
  private final List<PropuestaModificacionGuardaRopa> propuestasPrendientes = new ArrayList<>();
  private final List<PropuestaModificacionGuardaRopa> propuestasAceptadas = new ArrayList<>();

  public GuardaRopa(String idUsuario) {
    this.id = UUID.randomUUID().toString();
    this.idUsuarios = new ArrayList<>();
    this.idUsuarios.add(idUsuario);
    this.prendas = new ArrayList<>();
  }

  public GuardaRopa(String idUsuario, List<Prenda> prendas) {
    this.id = UUID.randomUUID().toString();
    this.idUsuarios = new ArrayList<>();
    this.idUsuarios.add(idUsuario);
    this.prendas = prendas;
  }

  public void agregarPrenda(Prenda prenda) {
    this.prendas.add(prenda);
  }

  public void quitarPrenda(Prenda prenda) {
    this.prendas.remove(prenda);
  }

  public boolean usuarioPertenece(String idUsuario) {
    return this.idUsuarios.contains(idUsuario);
  }

  public void crearPropuesta(PropuestaModificacionGuardaRopa propuesta) {
    this.propuestasPrendientes.add(propuesta);
  }

  public void agregarUsuario(String idUsuario) {
    if (!this.idUsuarios.contains(idUsuario)) {
      this.idUsuarios.add(idUsuario);
    }
  }

  public List<Outfit> generarSugerencias(SuggestionEngine motor, int age) {
    return motor.generateSuggestions(this.prendas, age);
  }

  public String getId() {
    return id;
  }

  public List<Prenda> getPrendas() {
    return prendas;
  }

  public List<String> getIdUsuarios() {
    return idUsuarios;
  }
}

