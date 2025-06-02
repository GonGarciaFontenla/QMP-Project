package org.example.Propuestas;

import org.example.GuardaRopa;
import org.example.Prenda.Prenda;

public class PropuestaQuitar extends PropuestaModificacionGuardaRopa {
  public PropuestaQuitar(Prenda prenda) {
    super(prenda);
  }

  @Override
  public void aceptar(GuardaRopa guardaRopa) {
    guardaRopa.quitarPrenda(prenda);
  }

  @Override
  public void rechazar(GuardaRopa guardaRopa) {
    guardaRopa.agregarPrenda(prenda);
  }
}
