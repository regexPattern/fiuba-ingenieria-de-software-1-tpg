package psa.cargahoras.dto;

public class RecursoDTO {

  private String id;
  private String nombre;
  private String apellido;
  private String dni;
  private String rolId;

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

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getRolId() {
    return rolId;
  }

  public void setRolId(String rolId) {
    this.rolId = rolId;
  }
}
