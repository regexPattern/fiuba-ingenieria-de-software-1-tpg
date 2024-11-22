package psa.cargahoras.dto;

public class TareaDTO {

  private String id;
  private String nombre;
  private String descripcion;
  private String recursoId;
  private String proyectoId;

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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getRecursoId() {
    return recursoId;
  }

  public void setRecursoId(String recursoId) {
    this.recursoId = recursoId;
  }

  public String getProyectoId() {
    return proyectoId;
  }

  public void setProyectoId(String proyectoId) {
    this.proyectoId = proyectoId;
  }
}
