package psa.cargahoras;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class Proyecto {
  public enum Estado {
    // TODO: tenemos que definir esto en el diccionario de datos.
    Activo,
    Pausado,
    Finalizado
  }

  private final UUID id;
  private String nombre;
  private double coeficienteRiesgo;
  private Estado estado;
  private LocalDate fechaInicio;

  public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public Proyecto(String nombre, double coeficienteRiesgo, Estado estado, String fechaInicioStr) {
    if (coeficienteRiesgo < 0 || coeficienteRiesgo > 1) {
      throw new IllegalArgumentException(
          "Coeficiente de riesgo debe estar entre 0 y 1 (inclusive).");
    }

    try {
      this.fechaInicio = LocalDate.parse(fechaInicioStr, dateTimeFormatter);
    } catch (DateTimeParseException exception) {
      throw new IllegalArgumentException("Formato de fecha debe ser dd/MM/yyyy");
    }

    this.id = UUID.randomUUID();
    this.nombre = nombre;
    this.coeficienteRiesgo = coeficienteRiesgo;
    this.estado = estado;
  }

  public UUID getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public Double getCoeficienteRiesgo() {
    return coeficienteRiesgo;
  }

  public Estado getEstado() {
    return estado;
  }

  public LocalDate getFechaInicio() {
    return fechaInicio;
  }
}
