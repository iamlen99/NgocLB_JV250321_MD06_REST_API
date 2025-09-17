package ra.edu.security.principal;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ra.edu.model.entity.Roles;
import ra.edu.model.entity.Users;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserPrincipal implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private Boolean status;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails getUserDetails(Users users) {
        return UserPrincipal.builder()
                .id(users.getId())
                .username(users.getUsername())
                .password(users.getPassword())
                .fullName(users.getFullName())
                .email(users.getEmail())
                .address(users.getAddress())
                .phone(users.getPhone())
                .authorities(mapToGrandAuthorities(users.getRoles()))
                .build();
    }

    private static List<GrantedAuthority> mapToGrandAuthorities(Set<Roles> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
