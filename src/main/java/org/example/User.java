package org.example;

import org.example.Engine.SuggestionEngine;
import org.example.Prenda.*;
import org.example.Propuestas.PropuestaModificacionGuardaRopa;

import java.util.List;
import java.util.UUID;

public class User {
  private final String id;
  private final RepositorioGuardaRopa closet;
  private final SuggestionEngine motor;
  private final int age;

  public User(RepositorioGuardaRopa closet, int age, SuggestionEngine generadorSugerencias) {
    this.id = UUID.randomUUID().toString();
    this.closet = closet;
    this.age = age;
    this.motor = generadorSugerencias;
  }

  public void UploadItem(Prenda prenda, String idGuardaRopa) throws Exception {
    GuardaRopa guardaRopa = this.closet.obtenerPorGuardaropaId(idGuardaRopa);
    if(!guardaRopa.usuarioPertenece(this.id)) {
      throw new IllegalArgumentException("User does not belong to this closet");
    } else {
        guardaRopa.agregarPrenda(prenda);
    }
  }

  public void crearPropuesta(PropuestaModificacionGuardaRopa propuesta, String idGuardaropa) throws Exception {
    GuardaRopa guardaRopa = this.closet.obtenerPorGuardaropaId(idGuardaropa);
    if(!guardaRopa.usuarioPertenece(this.id)) {
      throw new IllegalArgumentException("User does not belong to this closet");
    } else {
      guardaRopa.crearPropuesta(propuesta);
    }
  }

  public List<GuardaRopa> GuardaRopasDeUsuario() {
    return this.closet.obtenerPorUsuarioId(this.id);
  }

  public List<Outfit> generarSugerencias(String idGuardarropa) throws Exception  {
    GuardaRopa guardarropa = this.closet.obtenerPorGuardaropaId(idGuardarropa);
    return guardarropa.generarSugerencias(this.motor, this.age);
  }

  public RepositorioGuardaRopa GetItems() {
    return closet;
  }

  public int getAge() {
    return age;
  }
}