package psa.cargahoras.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import java.util.UUID;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import psa.cargahoras.entity.CargaDeHoras;
import psa.cargahoras.repository.CargaDeHorasRepository;
import psa.cargahoras.service.ApiExternaService;
import psa.cargahoras.service.CargaDeHorasService;

public class CargaDeHorasSteps {

    private final ResultadoOperacionCommonSteps resultadoOperacion;

    private CargaDeHoras cargaDeHoras;
    private UUID recursoId;
    private UUID tareaId;

    @Mock
    private CargaDeHorasRepository cargaDeHorasRepository;

    @Autowired
    private CargaDeHorasService cargaDeHorasService;

    @Autowired
    private ApiExternaService apiExternaService;

    public CargaDeHorasSteps(ResultadoOperacionCommonSteps resultadoOperacion) {
        this.resultadoOperacion = resultadoOperacion;
    }

    @Before
    public void resetear() {
        MockitoAnnotations.openMocks(this);

        cargaDeHoras = null;

        cargaDeHorasService = new CargaDeHorasService(
            cargaDeHorasRepository,
            apiExternaService
        );
    }

    @Dado("un recurso con id {string}")
    public void dadoUnRecursoConId(String recursoId) {
        this.recursoId = UUID.fromString(recursoId);
    }

    @Y("una tarea activa con id {string}")
    public void dadaUnaTareaConId(String id) {
        this.tareaId = UUID.fromString(id);
    }

    @Cuando(
        "el recurso realiza una carga de {double} horas a la tarea en la fecha {string}"
    )
    public void cuandoElRecursoCargaHorasATareaEnFecha(
        double cantidadHoras,
        String fechaDateStr
    ) {
        cargaDeHoras = resultadoOperacion.ejecutar(() ->
            cargaDeHorasService.cargarHoras(
                tareaId,
                recursoId,
                cantidadHoras,
                fechaDateStr
            )
        );
    }

    @Y("la carga de horas debe ser registrada")
    public void verificarCargaDeHorasRegistrada() {
        assertNotNull(cargaDeHoras);
        verify(cargaDeHorasRepository).save(any(CargaDeHoras.class));
    }

    @Y("la carga de horas debe estar asociada el recurso con id {string}")
    public void verificarRecursoCargaDeHoras(String recursoId) {
        assertEquals(recursoId, cargaDeHoras.getRecursoId());
    }

    @Y("la carga de horas debe estar asociada a la tarea con id {string}")
    public void verificarTareaCargaDeHoras(String tareaId) {
        assertEquals(tareaId, cargaDeHoras.getTareaId());
    }

    @Y("la cantidad de horas de la carga de horas debe ser {double} horas")
    public void verificarCantidadHorasCargaDeHoras(double cantidadHoras) {
        assertEquals(cantidadHoras, cargaDeHoras.getCantidadHoras(), 0.0);
    }

    @Y("la fecha de carga de horas debe ser {string}")
    public void verificarFechaCargaDeHoras(String fechaDateStr) {
        assertEquals(
            fechaDateStr,
            cargaDeHoras.getFechaCarga().format(CargaDeHoras.formatterFecha)
        );
    }

    @Y(
        "una carga de {double} horas registradas para la tarea con id {string}, y recurso con id {string}, en la fecha {string}"
    )
    public void dadaUnaCargaDeHorasParaTareaDeRecurso(
        double cantidadHoras,
        String tareaId,
        String recursoId,
        String fechaDateStr
    ) {
        cargaDeHoras = new CargaDeHoras(
            UUID.fromString(tareaId),
            UUID.fromString(recursoId),
            cantidadHoras,
            fechaDateStr
        );
    }

    @Entonces("la carga de horas no debe ser registrada")
    public void verificarCargaDeHorasNoRegistrada() {
        verify(cargaDeHorasRepository, never()).save(any(CargaDeHoras.class));
    }

    @Entonces("el mensaje de error debe ser {string}")
    public void verificarMensajeDeError(String mensajeEsperado) {
        assertEquals(
            mensajeEsperado,
            resultadoOperacion.getExcepcion().getMessage()
        );
    }

    @Dado("un recurso con id inexistente {string}")
    public void dadoUnRecursoConIdInexistente(String recursoId) {
        // when(recursoRepository.findById(recurso.getId())).thenReturn(
        //     Optional.empty()
        // );
        // when(recursoRepository.existsById(recurso.getId())).thenReturn(false);
    }

    @Y("una tarea con id inexistente {string}")
    public void dadoUnaTareaConIdInexistente(String tareaId) {
        // tarea = new Tarea(
        //     UUID.fromString(tareaId),
        //     "Terminar el backend",
        //     "Se debe finalizar la creación de los endpoints y las entidades correspondientes",
        //     mock(Proyecto.class)
        // );

        // when(tareaRepository.findById(tarea.getId())).thenReturn(
        //     Optional.empty()
        // );
    }
}
