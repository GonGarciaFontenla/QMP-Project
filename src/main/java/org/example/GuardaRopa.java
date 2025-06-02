package org.example;

import org.example.Prenda.Prenda;

import java.util.List;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuardaRopa {
  private final String id;
  private final List<Prenda> prendas;
  private final List<String> idUsuarios;

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

