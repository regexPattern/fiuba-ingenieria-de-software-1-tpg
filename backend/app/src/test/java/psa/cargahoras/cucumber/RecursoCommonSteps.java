package psa.cargahoras.cucumber;

import static org.mockito.Mockito.*;

import io.cucumber.java.es.Dado;
import java.util.Arrays;
import psa.cargahoras.dto.RecursoDTO;

public class RecursoCommonSteps {
  private final TestContext testContext;
  // private final ROLCommonSteps rolCommonSteps;

  private RecursoDTO recurso;

  public RecursoCommonSteps(TestContext testContext /*ROLCommonSteps rolCommonSteps*/) {
    // this.rolCommonSteps = rolCommonSteps;
    this.testContext = testContext;
  }

  @Dado("un recurso con id {string}")
  public void dadoUnRecursoConId(String recursoId) {
    recurso = mock(RecursoDTO.class);
    when(recurso.getId()).thenReturn(recursoId);
    when(testContext.getApiExternaService().getRecursos()).thenReturn(Arrays.asList(recurso));
  }

  @Dado("un recurso con id inexistente {string}")
  public void dadoUnRecursoConIdInexistente(String recursoId) {
    recurso = mock(RecursoDTO.class);
    when(recurso.getId()).thenReturn(recursoId);
    when(testContext.getApiExternaService().getRecursos()).thenReturn(Arrays.asList());
  }

  @Dado("un recurso con id {string} que no ha cargado horas")
  public void dadoUnRecurso(String recursoId) {
    recurso = mock(RecursoDTO.class);
    when(recurso.getId()).thenReturn(recursoId);
    when(testContext.getApiExternaService().getRecursos()).thenReturn(Arrays.asList(recurso));
  }

  @Dado("un recurso con id {string}, con rol con id {string}")
  public void dadoUnRecurso(String recursoId, String rolId) {
    recurso = mock(RecursoDTO.class);
    when(recurso.getId()).thenReturn(recursoId);
    when(recurso.getRolId()).thenReturn(rolId);
    when(testContext.getApiExternaService().getRecursos()).thenReturn(Arrays.asList(recurso));
  }

  @Dado("un recurso con id inexistente {string}, con rol con id {string}")
  public void dadoUnRecursoConIdInexistenteConRolId(String recursoId, String rolId) {
    recurso = mock(RecursoDTO.class);
    when(recurso.getId()).thenReturn(recursoId);
    when(recurso.getRolId()).thenReturn(rolId);
    when(testContext.getApiExternaService().getRecursos()).thenReturn(Arrays.asList());
  }

  public RecursoDTO getRecurso() {
    return recurso;
  }
}
