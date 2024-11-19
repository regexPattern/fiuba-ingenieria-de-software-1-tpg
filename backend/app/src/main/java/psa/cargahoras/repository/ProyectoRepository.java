package psa.cargahoras.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psa.cargahoras.entity.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, UUID> {}
