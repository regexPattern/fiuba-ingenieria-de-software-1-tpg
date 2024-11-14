package psa.cargahoras.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tbl_tareas")
public class TareaEntity {
  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "id_proyecto", referencedColumnName = "id")
  private ProyectoEntity proyecto;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado", nullable = false)
  private EstadoTarea estado;

  @Column(name = "fecha_inicio")
  private LocalDateTime fechaInicio;

  @Column(name = "fecha_finalizacion")
  private LocalDateTime fechaFinalizacion;

  public enum EstadoTarea {
    SIN_EMPEZAR,
    EMPEZADA,
    PAUSADA,
    TERMINADA
  }

  public TareaEntity(String nombre, ProyectoEntity proyecto) {
    this.id = UUID.randomUUID();
    if (proyecto == null) {
      throw new NullPointerException("El proyecto pasado tiene que existir");
    }
    this.proyecto = proyecto;
    this.nombre = nombre;
    this.estado = EstadoTarea.SIN_EMPEZAR;
  }

  public UUID getId() {
    return id;
  }

  public ProyectoEntity getProyecto() {
    return proyecto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public EstadoTarea getEstado() {
    return estado;
  }

  public void setEstado(EstadoTarea estado) {
    this.estado = estado;
  }

  public java.time.LocalDateTime getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(java.time.LocalDateTime fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public java.time.LocalDateTime getFechaFinalizacion() {
    return fechaFinalizacion;
  }

  public void setFechaFinalizacion(java.time.LocalDateTime fechaFinalizacion) {
    this.fechaFinalizacion = fechaFinalizacion;
  }
}
