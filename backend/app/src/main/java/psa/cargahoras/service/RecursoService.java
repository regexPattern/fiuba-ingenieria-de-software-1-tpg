package psa.cargahoras.service;

import java.util.List;

import org.springframework.stereotype.Service;

import psa.cargahoras.dto.CargaDeHorasPorRecursoDTO;
import psa.cargahoras.dto.RecursoDTO;
import psa.cargahoras.dto.RolDTO;

@Service
public class RecursoService {

  private final ApiExternaService apiExternaService;
  private final CargaDeHorasService cargaDeHorasService;

  public RecursoService(ApiExternaService apiExternaService, CargaDeHorasService cargaDeHorasService) {
    this.apiExternaService = apiExternaService;
    this.cargaDeHorasService = cargaDeHorasService;
  }

  public List<Integer> obtenerCostosDeTodosLosRecursos() {
    return apiExternaService.getRoles().stream().
        map(rol -> rol.getCosto()).
        toList();
  }

  public Integer obtenerCostoPorRecurso(String recursoId) {
    boolean existeRecurso = apiExternaService.getRecursos().stream()
        .anyMatch(recurso -> recurso.getId().equals(recursoId));

    if (!existeRecurso) {
      throw new IllegalArgumentException("No existe el recurso con el ID: " + recursoId);
    }

    RecursoDTO recursoBuscado = apiExternaService.getRecursos().stream()
        .filter(recurso -> recurso.getId().equals(recursoId))
        .limit(1).findFirst().orElse(null);

    RolDTO rolRecurso = apiExternaService.getRoles().stream()
        .filter(rol -> rol.getId().equals(recursoBuscado.getRolId()))
        .findFirst().orElse(null);

    List<CargaDeHorasPorRecursoDTO> cargasDeHoras = cargaDeHorasService.obtenerCargasDeHorasPorRecurso(recursoId);

    List<Double> horasCargadas = cargasDeHoras.stream().map(carga -> carga.getCantidadHoras()).toList();
    Double costoRecurso = horasCargadas.stream()
                            .mapToDouble(hora -> rolRecurso.getCosto() * hora).sum();

    // System.out.println("Costo por hora: " + rolRecurso.getCosto() + ", horas cargadas: " + horasCargadas.get(0) + ". Costo total del recurso:" + rolRecurso.getCosto()*horasCargadas.get(0));
    
  return costoRecurso.intValue();
  }
        
}
