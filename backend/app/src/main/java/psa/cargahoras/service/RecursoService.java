package psa.cargahoras.service;

import java.util.List;
import org.springframework.stereotype.Service;
import psa.cargahoras.dto.CargaDeHorasPorRecursoDTO;
import psa.cargahoras.dto.CostoRecursoDTO;
import psa.cargahoras.dto.RecursoDTO;
import psa.cargahoras.dto.RolDTO;
import psa.cargahoras.entity.CargaDeHoras;

@Service
public class RecursoService {

  private final ApiExternaService apiExternaService;
  private final CargaDeHorasService cargaDeHorasService;

  private RecursoDTO obtenerRecurso(String recursoId) {
    return
        apiExternaService.getRecursos().stream()
            .filter(recurso -> recurso.getId().equals(recursoId))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("No existe el recurso con ID: " + recursoId));
  }

  private RolDTO obtenerRol(String rolId) {
    return 
        apiExternaService.getRoles().stream()
          .filter(rol -> rol.getId().equals(rolId))
          .findFirst()
          .orElse(null);
  }

  private CostoRecursoDTO obtenerCosto(List<Double> horasCargadas, RecursoDTO recurso, RolDTO rol) {
    Double costoRecurso =
        horasCargadas.stream().mapToDouble(hora -> rol.getCosto() * hora).sum();

    return new CostoRecursoDTO(
        recurso.getId(),
        rol.getId(),
        costoRecurso.intValue(),
        String.join(" ", recurso.getNombre(), recurso.getApellido()),
        String.join(" ", rol.getNombre(), rol.getExperiencia()));
  }

  public RecursoService(
      ApiExternaService apiExternaService, CargaDeHorasService cargaDeHorasService) {
    this.apiExternaService = apiExternaService;
    this.cargaDeHorasService = cargaDeHorasService;
  }

  public List<CostoRecursoDTO> obtenerCostosDeTodosLosRecursos() {
    List<RecursoDTO> recursos = apiExternaService.getRecursos();
    return recursos.stream().map(recurso -> obtenerCostoPorRecurso(recurso.getId())).toList();
  }

  public CostoRecursoDTO obtenerCostoPorRecurso(String recursoId) {
    RecursoDTO recursoBuscado = obtenerRecurso(recursoId);
    RolDTO rolRecurso = obtenerRol(recursoBuscado.getRolId());
    
    List<CargaDeHorasPorRecursoDTO> cargasDeHoras =
        cargaDeHorasService.obtenerCargasDeHorasPorRecurso(recursoId, null);

    List<Double> horasCargadas =
        cargasDeHoras.stream().map(carga -> carga.getCantidadHoras()).toList();

    return obtenerCosto(horasCargadas, recursoBuscado, rolRecurso);
  }

  public CostoRecursoDTO obtenerCostoPorRecurso(String recursoId, List<CargaDeHoras> cargasDeHoras) {
    RecursoDTO recursoBuscado = obtenerRecurso(recursoId);
    RolDTO rolRecurso = obtenerRol(recursoBuscado.getRolId());
    
    List<Double> horasCargadas =
        cargasDeHoras.stream().map(carga -> carga.getCantidadHoras()).toList();

    return obtenerCosto(horasCargadas, recursoBuscado, rolRecurso);
  }

  public List<CostoRecursoDTO> obtenerCostosDeTodosLosRecursos(List<CargaDeHoras> cargasDeHoras) {
    List<RecursoDTO> recursos = apiExternaService.getRecursos();
    return recursos.stream().map(recurso -> obtenerCostoPorRecurso(recurso.getId(), cargasDeHoras)).toList();    
  }
}
