package psa.cargahoras;

import java.util.UUID;

public class Tarea {
  private final UUID id;

  public Tarea() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }
}
