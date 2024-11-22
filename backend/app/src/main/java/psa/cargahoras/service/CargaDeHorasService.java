package psa.cargahoras.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psa.cargahoras.entity.CargaDeHoras;
import psa.cargahoras.repository.CargaDeHorasRepository;

@Service
public class CargaDeHorasService {

  @Autowired private CargaDeHorasRepository cargaHorasRepository;

  @Autowired private ApiExternaService apiExternaService;

  public CargaDeHorasService(
      CargaDeHorasRepository cargaDeHorasRepository, ApiExternaService apiExternaService) {
    this.cargaHorasRepository = cargaDeHorasRepository;
    this.apiExternaService = apiExternaService;
  }

  public List<CargaDeHoras> obtenerTodasLasCargas() {
    return cargaHorasRepository.findAll();
  }

  public CargaDeHoras obtenerCargaDeHorasPorId(UUID id) {
    return cargaHorasRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No existe carga con ID: " + id));
  }

  public CargaDeHoras cargarHoras(
      UUID tareaId, UUID recursoId, double cantidadDeHoras, String fechaCargaStr) {
    boolean existeTarea =
        apiExternaService.getTareas().stream().anyMatch(t -> t.getId().equals(tareaId.toString()));

    if (!existeTarea) {
      throw new IllegalArgumentException("No existe la tarea con ID: " + tareaId);
    }

    boolean existeRecurso =
        apiExternaService.getRecursos().stream()
            .anyMatch(r -> r.getId().equals(recursoId.toString()));

    if (!existeRecurso) {
      throw new IllegalArgumentException("No existe el recurso con ID: " + recursoId);
    }

    List<CargaDeHoras> cargasExistentes = cargaHorasRepository.findByTareaId(tareaId);

    if (!cargasExistentes.isEmpty() && !cargasExistentes.get(0).getRecursoId().equals(recursoId)) {
      throw new IllegalArgumentException(
          "Esta tarea ya está asignada al recurso con ID: "
              + cargasExistentes.get(0).getRecursoId());
    }

    CargaDeHoras nuevaCarga = new CargaDeHoras(tareaId, recursoId, cantidadDeHoras, fechaCargaStr);

    cargaHorasRepository.save(nuevaCarga);

    return nuevaCarga;
  }
}
