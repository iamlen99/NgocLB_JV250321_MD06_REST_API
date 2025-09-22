package ra.edu.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Roles;
import ra.edu.repository.RoleRepository;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleRepository roleRepository;

    public Set<Roles> mapToSetRole(Set<String> roles) {
        Set<Roles> roleSet = new HashSet<>();
        if (roles == null || roles.isEmpty()) {
            roleSet.add(roleRepository.findByRoleName(RoleName.STUDENT)
                    .orElseThrow(() -> new NoSuchElementException("Không tồn tại role STUDENT")));
        } else {
            roles.forEach(role -> {
                roleSet.add(roleRepository.findByRoleName(RoleName.valueOf(role.toUpperCase()))
                        .orElseThrow(() -> new NoSuchElementException("Không tồn tại role :" + role)));
            });
        }
        return roleSet;
    }
}
