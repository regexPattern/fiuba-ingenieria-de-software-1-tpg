package psa.cargahoras.dto;

import java.util.Arrays;

public class RolDTO {

  private String id;
  private String nombre;
  private String experiencia;
  private Integer costo;

  private Integer definirCosto() {
    switch (String.join(" ", Arrays.asList(nombre, experiencia))) {
      case "Desarrollador Senior":
        return 30;
      case "Desarrollador Semi-Senior":
        return 25;
      case "Desarrollador Junior":
        return 20;
      case "Analista nivel I":
        return 20;
      case "Analista nivel II":
        return 25;
      default:
        return 30;
    }
  }

  public RolDTO() {
    this.costo = definirCosto();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getExperiencia() {
    return experiencia;
  }

  public void setExperiencia(String experiencia) {
    this.experiencia = experiencia;
  }

  public Integer getCosto() {
    return costo;
  }

  public void setCosto(Integer costo) {
    this.costo = costo;
  }
}
