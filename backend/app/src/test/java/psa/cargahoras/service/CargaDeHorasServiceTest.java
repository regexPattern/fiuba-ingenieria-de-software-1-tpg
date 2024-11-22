package psa.cargahoras.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import psa.cargahoras.dto.RecursoDTO;
import psa.cargahoras.dto.TareaDTO;
import psa.cargahoras.entity.CargaDeHoras;
import psa.cargahoras.repository.CargaDeHorasRepository;

@RunWith(MockitoJUnitRunner.class)
public class CargaDeHorasServiceTest {

  @Mock private CargaDeHorasRepository cargaDeHorasRepository;

  @Mock private ApiExternaService apiExternaService;

  @InjectMocks private CargaDeHorasService cargaDeHorasService;

  @Test
  public void cargarHorasAUnaMismaTareaConUnMismoRecurso() {
    UUID recursoId = UUID.randomUUID();
    UUID tareaId = UUID.randomUUID();

    double cantidadHoras = 8.0;
    String fechaCargaStr = "19/11/2024";

    TareaDTO tarea = new TareaDTO();
    tarea.setId(tareaId.toString());

    RecursoDTO recurso = new RecursoDTO();
    recurso.setId(recursoId.toString());

    when(apiExternaService.getTareas()).thenReturn(Arrays.asList(tarea));
    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList(recurso));

    cargaDeHorasService.cargarHoras(tareaId, recursoId, cantidadHoras, fechaCargaStr);
    cargaDeHorasService.cargarHoras(tareaId, recursoId, cantidadHoras, fechaCargaStr);

    verify(cargaDeHorasRepository, times(2)).save(any(CargaDeHoras.class));
  }

  public void cargarHorasAUnaMismaTareaConDistintosRecursos() {
    UUID recurso1Id = UUID.randomUUID();
    UUID recurso2Id = UUID.randomUUID();
    UUID tareaId = UUID.randomUUID();

    double cantidadHoras = 8.0;
    String fechaCargaStr = "19/11/2024";

    TareaDTO tarea = new TareaDTO();
    tarea.setId(tareaId.toString());

    RecursoDTO recurso1 = new RecursoDTO();
    recurso1.setId(recurso1Id.toString());

    RecursoDTO recurso2 = new RecursoDTO();
    recurso2.setId(recurso2Id.toString());

    when(apiExternaService.getTareas()).thenReturn(Arrays.asList(tarea));
    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList(recurso1, recurso2));

    cargaDeHorasService.cargarHoras(tareaId, recurso1Id, cantidadHoras, fechaCargaStr);

    cargaDeHorasService.cargarHoras(tareaId, recurso2Id, cantidadHoras, fechaCargaStr);

    verify(cargaDeHorasRepository, times(2)).save(any(CargaDeHoras.class));
  }

  @Test
  public void cargarHorasADistintasTareasConUnMismoRecurso() {
    double cantidadHoras = 8.0;
    String fechaCargaStr = "19/11/2024";

    UUID tarea1Id = UUID.randomUUID();
    UUID tarea2Id = UUID.randomUUID();
    UUID recursoId = UUID.randomUUID();

    TareaDTO tarea1 = new TareaDTO();
    tarea1.setId(tarea1Id.toString());

    TareaDTO tarea2 = new TareaDTO();
    tarea2.setId(tarea2Id.toString());

    RecursoDTO recurso = new RecursoDTO();
    recurso.setId(recursoId.toString());

    when(apiExternaService.getTareas()).thenReturn(Arrays.asList(tarea1, tarea2));
    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList(recurso));

    cargaDeHorasService.cargarHoras(tarea1Id, recursoId, cantidadHoras, fechaCargaStr);

    cargaDeHorasService.cargarHoras(tarea2Id, recursoId, cantidadHoras, fechaCargaStr);

    verify(cargaDeHorasRepository, times(2)).save(any(CargaDeHoras.class));
  }

  public void obtenerTodasLasCargasDeHoras() {
    double cantidadHoras = 8.0;
    String fechaCargaStr = "19/11/2024";

    UUID recursoId = UUID.randomUUID();
    UUID tareaId = UUID.randomUUID();

    TareaDTO tarea = new TareaDTO();
    tarea.setId(tareaId.toString());

    RecursoDTO recurso = new RecursoDTO();
    recurso.setId(recursoId.toString());

    when(apiExternaService.getTareas()).thenReturn(Arrays.asList(tarea));
    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList(recurso));

    CargaDeHoras carga1 =
        cargaDeHorasService.cargarHoras(tareaId, recursoId, cantidadHoras, fechaCargaStr);
    CargaDeHoras carga2 =
        cargaDeHorasService.cargarHoras(tareaId, recursoId, cantidadHoras, fechaCargaStr);

    List<CargaDeHoras> cargasGuardadas = cargaDeHorasService.obtenerTodasLasCargas();

    assertNotNull(cargasGuardadas);
    assertEquals(2, cargasGuardadas.size());
    assertEquals(cargasGuardadas.get(0).getId(), carga1.getId());
    assertEquals(cargasGuardadas.get(1).getId(), carga2.getId());
  }

  @Test
  public void cargarHorasATareaInexistenteTiraExcepcion() {
    UUID tareaId = UUID.randomUUID();
    UUID recursoId = UUID.randomUUID();

    RecursoDTO recurso = new RecursoDTO();
    recurso.setId(recursoId.toString());

    when(apiExternaService.getTareas()).thenReturn(Arrays.asList());

    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> cargaDeHorasService.cargarHoras(tareaId, recursoId, 8.0, "19/11/2024"));

    assertEquals("No existe la tarea con ID: " + tareaId, e.getMessage());

    verify(cargaDeHorasRepository, never()).save(any());
  }

  @Test
  public void cargarHorasDeRecursoInexistenteTiraExcepcion() {
    UUID recursoId = UUID.randomUUID();
    UUID tareaId = UUID.randomUUID();

    TareaDTO tarea = new TareaDTO();
    tarea.setId(tareaId.toString());

    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList());
    when(apiExternaService.getTareas()).thenReturn(Arrays.asList(tarea));

    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> cargaDeHorasService.cargarHoras(tareaId, recursoId, 8.0, "19/11/2024"));

    assertEquals("No existe el recurso con ID: " + recursoId, e.getMessage());

    verify(cargaDeHorasRepository, never()).save(any());
  }
}
