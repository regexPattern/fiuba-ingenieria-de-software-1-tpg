package psa.cargahoras.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "tbl_carga_de_horas")
public class CargaDeHoras {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private UUID tareaId;

  @Column(nullable = false)
  private UUID recursoId;

  @Column(nullable = false)
  private double cantidadHoras;

  @Column(nullable = false)
  private LocalDate fechaCarga;

  public static DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  protected CargaDeHoras() {}

  public CargaDeHoras(UUID tareaId, UUID recursoId, double cantidadHoras, String fechaCargaStr) {
    if (cantidadHoras < 0) {
      throw new IllegalArgumentException("La cantidad de horas no puede ser negativa");
    }
    if (tareaId == null) {
      throw new IllegalArgumentException("El id de la tarea no puede ser nulo");
    }
    if (recursoId == null) {
      throw new IllegalArgumentException("El id del recurso no puede ser nulo");
    }
    if (fechaCargaStr == null) {
      throw new IllegalArgumentException("La fecha de carga no puede ser nula");
    }

    try {
      this.fechaCarga = LocalDate.parse(fechaCargaStr, formatterFecha);
    } catch (Exception e) {
      throw new IllegalArgumentException("El formato de la fecha debe ser dd/MM/yyyy", e);
    }

    this.tareaId = tareaId;
    this.recursoId = recursoId;
    this.cantidadHoras = cantidadHoras;
  }

  public UUID getId() {
    return id;
  }

  public UUID getTareaId() {
    return tareaId;
  }

  public UUID getRecursoId() {
    return recursoId;
  }

  public double getCantidadHoras() {
    return cantidadHoras;
  }

  public LocalDate getFechaCarga() {
    return fechaCarga;
  }
}
