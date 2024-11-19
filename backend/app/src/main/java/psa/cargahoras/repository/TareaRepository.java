package psa.cargahoras.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psa.cargahoras.entity.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, UUID> {}
