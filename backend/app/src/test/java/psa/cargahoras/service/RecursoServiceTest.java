package psa.cargahoras.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import psa.cargahoras.dto.RecursoDTO;
import psa.cargahoras.dto.RolDTO;
import psa.cargahoras.entity.CargaDeHoras;
import psa.cargahoras.repository.CargaDeHorasRepository;
import psa.cargahoras.dto.CargaDeHorasPorRecursoDTO;

@RunWith(MockitoJUnitRunner.class)
public class RecursoServiceTest {

  @Mock private ApiExternaService apiExternaService;
  @Mock private CargaDeHorasService mockCargaDeHorasService;
  @Mock private CargaDeHorasRepository cargaDeHorasRepository;

  @InjectMocks private RecursoService recursoService;

  @Test
  public void obtenerCostosDeTodosLosRecursos() {
    String rolId1 = UUID.randomUUID().toString();
    String rolId2 = UUID.randomUUID().toString();

    String recursoId1 = UUID.randomUUID().toString();
    String recursoId2 = UUID.randomUUID().toString();

    RolDTO rol1 = new RolDTO();
    rol1.setId(rolId1);
    RolDTO rol2 = new RolDTO();
    rol2.setId(rolId2);

    RecursoDTO recurso1 = new RecursoDTO();
    recurso1.setId(recursoId1);
    RecursoDTO recurso2 = new RecursoDTO();
    recurso2.setId(recursoId2);

    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList(recurso1, recurso2));
    when(apiExternaService.getRoles()).thenReturn(Arrays.asList(rol1, rol2));

    List<Integer> costosRecursos = recursoService.obtenerCostosDeTodosLosRecursos();

    assertEquals(2, costosRecursos.size());
    assertEquals(30, costosRecursos.get(0), 0.1);
    assertEquals(30, costosRecursos.get(1), 0.1);
  }


  @Test
  public void obtenerCostoPorRecurso() {
    String rolId = UUID.randomUUID().toString();
    String recursoId = UUID.randomUUID().toString();
    String cargaDeHorasId = UUID.randomUUID().toString();

    RolDTO rol = new RolDTO();
    rol.setId(rolId);

    RecursoDTO recurso = new RecursoDTO();
    recurso.setId(recursoId);
    recurso.setRolId(rolId);

    CargaDeHoras cargaDeHoras = new CargaDeHoras(cargaDeHorasId, recursoId, 8.0, "26/11/2024");

    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList(recurso));
    when(cargaDeHorasRepository.findAll()).thenReturn(Arrays.asList(cargaDeHoras));

    CargaDeHorasService cargaDeHorasService = new CargaDeHorasService(cargaDeHorasRepository, apiExternaService);
    List<CargaDeHorasPorRecursoDTO> cargaPorRecurso = cargaDeHorasService.obtenerCargasDeHorasPorRecurso(recursoId);

    
    when(apiExternaService.getRoles()).thenReturn(Arrays.asList(rol));
    when(mockCargaDeHorasService.obtenerCargasDeHorasPorRecurso(recursoId)).thenReturn(cargaPorRecurso);

    long costoRecursos = recursoService.obtenerCostoPorRecurso(recurso.getId());

    assertEquals(240, costoRecursos, 0.1);
  }

  @Test
  public void obtenerCostoDeUnRecursoInexistenteTiraExcepcion() {
    String recursoInexistenteId = UUID.randomUUID().toString();

    when(apiExternaService.getRecursos()).thenReturn(Arrays.asList());

    Exception e =
      assertThrows(IllegalArgumentException.class, 
          () ->recursoService.obtenerCostoPorRecurso(recursoInexistenteId));

      assertEquals("No existe el recurso con el ID: " + recursoInexistenteId, e.getMessage());

  }
}
