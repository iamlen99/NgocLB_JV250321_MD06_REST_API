package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Page<Users> findAllByRolesRoleName(RoleName rolesRoleName, Pageable pageable);

    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
