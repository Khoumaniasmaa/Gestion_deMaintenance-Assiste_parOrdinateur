package ma.emsi.gmao.repositories;

import ma.emsi.gmao.entities.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionRepository extends JpaRepository<Intervention,Long> {
  Page<Intervention> findByNomContains(String Kw, Pageable pageable);

}
