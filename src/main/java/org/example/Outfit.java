package org.example;

import org.example.Prenda.Prenda;

public class Outfit {
  //An outfit has to be composed by at least one garment of each category -except accesorys-
  private final Prenda calzado;
  private final Prenda parteSuperior;
  private final Prenda parteInferior;
  private final Prenda accesorio;

  public Outfit(Prenda calzado, Prenda parteSuperior, Prenda parteInferior, Prenda accesorio) {
    this.calzado = calzado;
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.accesorio = accesorio;
  }

  public Outfit(Prenda calzado, Prenda parteSuperior, Prenda parteInferior) {
    this(calzado, parteSuperior, parteInferior, null);
  }

  public Prenda getCalzado() {
    return calzado;
  }
  public Prenda getParteSuperior() {
    return parteSuperior;
  }
  public Prenda getParteInferior() {
    return parteInferior;
  }
  public Prenda getAccesorio() {
    return accesorio;
  }
}
