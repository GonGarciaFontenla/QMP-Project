package org.example.Propuestas;

import org.example.GuardaRopa;
import org.example.Prenda.Prenda;

public abstract class PropuestaModificacionGuardaRopa {
  protected Prenda prenda;

  public PropuestaModificacionGuardaRopa(Prenda prenda) {
    this.prenda = prenda;
  }

  public abstract void aceptar(GuardaRopa guardaRopa);

  public abstract void rechazar(GuardaRopa guardaRopa);
}
