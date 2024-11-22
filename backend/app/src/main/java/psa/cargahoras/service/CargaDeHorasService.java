package psa.cargahoras.service;

import java.util.List;
import org.springframework.stereotype.Service;
import psa.cargahoras.dto.TareaDTO;
import psa.cargahoras.entity.CargaDeHoras;
import psa.cargahoras.repository.CargaDeHorasRepository;

@Service
public class CargaDeHorasService {

  private final CargaDeHorasRepository cargaHorasRepository;
  private final ApiExternaService apiExternaService;

  public CargaDeHorasService(
      CargaDeHorasRepository cargaHorasRepository, ApiExternaService apiExternaService) {
    this.cargaHorasRepository = cargaHorasRepository;
    this.apiExternaService = apiExternaService;
  }

  public List<CargaDeHoras> obtenerTodasLasCargasDeHoras() {
    return cargaHorasRepository.findAll();
  }

  public List<CargaDeHoras> obtenerTodasLasCargasDeHorasPorProyecto(String proyectoId) {
    boolean existeProyecto =
        apiExternaService.getProyectos().stream()
            .anyMatch(proyecto -> proyecto.getId().equals(proyectoId));

    if (!existeProyecto) {
      throw new IllegalArgumentException("No existe el proyecto con ID: " + proyectoId);
    }

    List<TareaDTO> tareasDelProyecto =
        apiExternaService.getTareas().stream()
            .filter(tarea -> tarea.getProyectoId().equals(proyectoId))
            .toList();

    List<CargaDeHoras> cargasDeHoras =
        cargaHorasRepository.findAll().stream()
            .filter(
                carga ->
                    tareasDelProyecto.stream()
                        .anyMatch(tarea -> tarea.getId().equals(carga.getTareaId())))
            .toList();

    return cargasDeHoras;
  }

  public CargaDeHoras obtenerCargaDeHorasPorId(String id) {
    return cargaHorasRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No existe carga con ID: " + id));
  }

  public CargaDeHoras cargarHoras(CargaDeHoras nuevaCarga) {
    boolean existeTarea =
        apiExternaService.getTareas().stream()
            .anyMatch(tarea -> tarea.getId().equals(nuevaCarga.getTareaId().toString()));

    if (!existeTarea) {
      throw new IllegalArgumentException("No existe la tarea con ID: " + nuevaCarga.getTareaId());
    }

    boolean existeRecurso =
        apiExternaService.getRecursos().stream()
            .anyMatch(recurso -> recurso.getId().equals(nuevaCarga.getRecursoId().toString()));

    if (!existeRecurso) {
      throw new IllegalArgumentException(
          "No existe el recurso con ID: " + nuevaCarga.getRecursoId());
    }

    List<CargaDeHoras> cargasExistentes =
        cargaHorasRepository.findByTareaId(nuevaCarga.getTareaId());

    if (!cargasExistentes.isEmpty()
        && !cargasExistentes.get(0).getRecursoId().equals(nuevaCarga.getRecursoId())) {
      throw new IllegalArgumentException(
          "Esta tarea ya está asignada al recurso con ID: "
              + cargasExistentes.get(0).getRecursoId());
    }

    cargaHorasRepository.save(nuevaCarga);

    return nuevaCarga;
  }
}
