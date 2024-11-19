package psa.cargahoras.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import psa.cargahoras.dto.ProyectoDTO;
import psa.cargahoras.dto.RecursoDTO;
import psa.cargahoras.dto.RolDTO;
import psa.cargahoras.dto.TareaDTO;

@Service
public class APIService {
  private final RestTemplate restTemplate;
  private final String API_RECURSOS =
      "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/recursos-api/1.0.1/m/recursos";
  private final String API_TAREAS =
      "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/tareas-api/1.0.0/m/tareas";
  private final String API_ROLES =
      "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/roles-api/1.0.0/m/roles";
  private final String API_PROYECTOS =
      "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/proyectos-api/1.0.0/m/proyectos";

  public APIService() {
    this.restTemplate = new RestTemplate();
  }

  public List<RecursoDTO> obtenerRecursos() {
    ResponseEntity<RecursoDTO[]> response =
        restTemplate.getForEntity(API_RECURSOS, RecursoDTO[].class);
    return Arrays.asList(response.getBody());
  }

  public List<TareaDTO> obtenerTareas() {
    ResponseEntity<TareaDTO[]> response = restTemplate.getForEntity(API_TAREAS, TareaDTO[].class);
    return Arrays.asList(response.getBody());
  }

  public List<RolDTO> obtenerRoles() {
    ResponseEntity<RolDTO[]> response = restTemplate.getForEntity(API_ROLES, RolDTO[].class);
    return Arrays.asList(response.getBody());
  }

  public List<ProyectoDTO> obtenerProyectos() {
    ResponseEntity<ProyectoDTO[]> response =
        restTemplate.getForEntity(API_PROYECTOS, ProyectoDTO[].class);
    return Arrays.asList(response.getBody());
  }
}
