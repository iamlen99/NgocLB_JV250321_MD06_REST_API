package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Page<Service> findAllByAnimal_IdAndNameContaining(Long animalId, String name, Pageable pageable);

    Page<Service> findAllByNameContaining(String name, Pageable pageable);
}
