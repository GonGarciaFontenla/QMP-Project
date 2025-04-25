package org.example;

import org.example.Garment.Garment;

import java.util.ArrayList;

public class Outfit {
  //An outfit has to be composed by at least one garment of each category -except accesorys-
  private final Garment calzado;
  private final Garment parteSuperior;
  private final Garment parteInferior;
  private final Garment accesorio;

  public Outfit(Garment calzado, Garment parteSuperior, Garment parteInferior, Garment accesorio) {
    this.calzado = calzado;
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.accesorio = accesorio;
  }

  public Outfit(Garment calzado, Garment parteSuperior, Garment parteInferior) {
    this(calzado, parteSuperior, parteInferior, null);
  }

  public Garment getCalzado() {
    return calzado;
  }
  public Garment getParteSuperior() {
    return parteSuperior;
  }
  public Garment getParteInferior() {
    return parteInferior;
  }
  public Garment getAccesorio() {
    return accesorio;
  }
}
