package ra.edu.securirty.principal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Roles;
import ra.edu.model.entity.Users;
import ra.edu.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceCustom implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return UserPrincipal.builder()
                .userId(users.getUserId())
                .username(users.getUsername())
                .password(users.getPassword())
                .fullName(users.getFullName())
                .email(users.getEmail())
                .phone(users.getPhone())
                .authorities(getGrantedAuthorities(users.getRoles()))
                .isActive(users.getIsActive() != null ? users.getIsActive() : false)
                .createdAt(users.getCreatedAt())
                .updatedAt(users.getUpdatedAt())
                .build();
    }

    private static Set<GrantedAuthority> getGrantedAuthorities(Set<Roles> roles) {
        return roles.stream()
                .map((role) -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().name()))
                .collect(Collectors.toSet());
    }
}
