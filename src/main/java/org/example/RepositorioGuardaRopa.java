package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioGuardaRopa {
  private List<GuardaRopa> guardaRopa;

  public GuardaRopa obtenerPorGuardaropaId(String idGuardarropa) throws Exception {
    List<GuardaRopa> guardarropas = this.guardaRopa.stream().filter(guardarropa -> guardarropa.id() == idGuardarropa).collect(Collectors.toList());
    if(guardarropas.isEmpty())
      throw new Exception();
    return guardarropas.get(0);
  }

  public List<GuardaRopa> obtenerPorUsuarioId(String idUsuario) {
    return this.guardaRopa.stream().filter(guardarropa -> guardarropa.usuarioPertenece(idUsuario)).collect(Collectors.toList());
  }

  public void nuevo(GuardaRopa guardaRopa) {
    this.guardaRopa.add(guardaRopa);
  }
}