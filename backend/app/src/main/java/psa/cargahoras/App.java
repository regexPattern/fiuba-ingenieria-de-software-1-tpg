package psa.cargahoras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

  @PostMapping("/cargar")
  public ResponseEntity<String> cargarHoras() {
    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
