package psa.cargahoras;

import java.util.UUID;

public class CargaHoras {
  private final UUID id;
  private final Tarea tarea;
  private final Empleado empleado;

  public CargaHoras(Tarea tarea, Empleado empleado) {
    this.id = UUID.randomUUID();
    this.tarea = tarea;
    this.empleado = empleado;
  }

  public UUID getId() {
    return id;
  }
}
