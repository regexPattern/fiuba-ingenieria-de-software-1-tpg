package psa.cargahoras.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import psa.cargahoras.dto.*;

@Service
public class ApiExternaService {

  private static final String BASE_URL =
      "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4";

  private static final String RECURSOS_ENDPOINT = BASE_URL + "/recursos-api/1.0.1/m/recursos";
  private static final String TAREAS_ENDPOINT = BASE_URL + "/tareas-api/1.0.0/m/tareas";
  private static final String ROLES_ENDPOINT = BASE_URL + "/roles-api/1.0.0/m/roles";
  private static final String PROYECTOS_ENDPOINT = BASE_URL + "/proyectos-api/1.0.0/m/proyectos";

  private final RestTemplate clienteRest;

  @Autowired
  public ApiExternaService(RestTemplate clienteRest) {
    this.clienteRest = clienteRest;
  }

  public List<RecursoDTO> getRecursos() {
    RecursoDTO[] response = clienteRest.getForObject(RECURSOS_ENDPOINT, RecursoDTO[].class);
    return Arrays.asList(response);
  }

  public List<TareaDTO> getTareas() {
    TareaDTO[] response = clienteRest.getForObject(TAREAS_ENDPOINT, TareaDTO[].class);
    return Arrays.asList(response);
  }

  public List<RolDTO> getRoles() {
    RolDTO[] response = clienteRest.getForObject(ROLES_ENDPOINT, RolDTO[].class);
    return Arrays.asList(response);
  }

  public List<ProyectoDTO> getProyectos() {
    ProyectoDTO[] response = clienteRest.getForObject(PROYECTOS_ENDPOINT, ProyectoDTO[].class);
    return Arrays.asList(response);
  }
}
