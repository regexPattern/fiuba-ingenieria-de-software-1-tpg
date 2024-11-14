package psa.cargahoras.entity;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class TareaEntityTest {

    @Test
    public void constructorTarea() {
      // Preparación
      String nombreProyecto = "Proyecto de prueba";
      ProyectoEntity proyecto = new ProyectoEntity(nombreProyecto, 0.5, ProyectoEntity.Estado.Activo, "11/11/2024");
      String nombreTarea = "Tarea de prueba";
  
      // Ejecución
      TareaEntity tarea = new TareaEntity(nombreTarea, proyecto);
  
      // Verificación
      assertNotNull(tarea.getId());
      assertEquals(nombreTarea, tarea.getNombre());
      assertEquals(proyecto, tarea.getProyecto());
      assertEquals(TareaEntity.EstadoTarea.SIN_EMPEZAR, tarea.getEstado());
      assertNull(tarea.getFechaInicio());
      assertNull(tarea.getFechaFinalizacion());
    }
  
    @Test
    public void setNombreTarea() {
      // Preparación
      String nombreProyecto = "Proyecto de prueba";
      ProyectoEntity proyecto = new ProyectoEntity(nombreProyecto, 0.5, ProyectoEntity.Estado.Activo, "11/11/2024");
      String nombreTarea = "Tarea de prueba";
      TareaEntity tarea = new TareaEntity(nombreTarea, proyecto);
      String nuevoNombre = "Nuevo nombre";
  
      // Ejecución
      tarea.setNombre(nuevoNombre);
  
      // Verificación
      assertEquals(nuevoNombre, tarea.getNombre());
    }
  
    @Test
    public void setEstadoTarea() {
      // Preparación
      String nombreProyecto = "Proyecto de prueba";
      ProyectoEntity proyecto = new ProyectoEntity(nombreProyecto, 0.5, ProyectoEntity.Estado.Activo, "11/11/2024");
      String nombreTarea = "Tarea de prueba";
      TareaEntity tarea = new TareaEntity(nombreTarea, proyecto);
      TareaEntity.EstadoTarea nuevoEstado = TareaEntity.EstadoTarea.EMPEZADA;
  
      // Ejecución
      tarea.setEstado(nuevoEstado);
  
      // Verificación
      assertEquals(nuevoEstado, tarea.getEstado());
    }
  
    @Test
    public void setFechaInicioTarea() {
      // Preparación
      String nombreProyecto = "Proyecto de prueba";
      ProyectoEntity proyecto = new ProyectoEntity(nombreProyecto, 0.5, ProyectoEntity.Estado.Activo, "11/11/2024");
      String nombreTarea = "Tarea de prueba";
      TareaEntity tarea = new TareaEntity(nombreTarea, proyecto);
      LocalDateTime fechaInicio = LocalDateTime.of(2024, 11, 12, 10, 0);
  
      // Ejecución
      tarea.setFechaInicio(fechaInicio);
  
      // Verificación
      assertEquals(fechaInicio, tarea.getFechaInicio());
    }
  
    @Test
    public void setFechaFinalizacionTarea() {
      // Preparación
      String nombreProyecto = "Proyecto de prueba";
      ProyectoEntity proyecto = new ProyectoEntity(nombreProyecto, 0.5, ProyectoEntity.Estado.Activo, "11/11/2024");
      String nombreTarea = "Tarea de prueba";
      TareaEntity tarea = new TareaEntity(nombreTarea, proyecto);
      LocalDateTime fechaFinalizacion = LocalDateTime.of(2024, 11, 12, 11, 0);
  
      // Ejecución
      tarea.setFechaFinalizacion(fechaFinalizacion);
  
      // Verificación
      assertEquals(fechaFinalizacion, tarea.getFechaFinalizacion());
    }
  }
