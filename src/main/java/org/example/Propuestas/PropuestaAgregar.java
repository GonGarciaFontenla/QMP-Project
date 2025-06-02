package org.example.Propuestas;

import org.example.GuardaRopa;
import org.example.Prenda.Prenda;

public class PropuestaAgregar extends PropuestaModificacionGuardaRopa {
  public PropuestaAgregar(Prenda prenda) {
    super(prenda);
  }

  @Override
  public void aceptar(GuardaRopa guardaRopa) {
    guardaRopa.agregarPrenda(prenda);
  }

  @Override
  public void rechazar(GuardaRopa guardaRopa) {
    guardaRopa.quitarPrenda(prenda);
  }
}
