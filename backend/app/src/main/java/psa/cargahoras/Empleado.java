package psa.cargahoras;

import java.util.UUID;

public class Empleado {
  private final UUID id;
  private String nombre;
  private String apellido;
  private String cargo;
  private Double salario;

  public Empleado(String nombre, String apellido, String cargo, Double salario) {
    this.id = UUID.randomUUID();
    this.nombre = nombre;
    this.apellido = apellido;
    this.cargo = cargo;
    this.salario = salario;
  }
}
