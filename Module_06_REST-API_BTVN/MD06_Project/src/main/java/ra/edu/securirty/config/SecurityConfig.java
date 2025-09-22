package ra.edu.securirty.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.edu.securirty.jwt.AccessDenied;
import ra.edu.securirty.jwt.JwtEntryPoint;
import ra.edu.securirty.jwt.JwtTokenFilter;
import ra.edu.securirty.principal.UserDetailServiceCustom;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailServiceCustom userDetailServiceCustom;
    private final JwtTokenFilter jwtTokenFilter;
    private final JwtEntryPoint jwtEntryPoint;
    private final AccessDenied accessDenied;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/internship_phases/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR")
                                .requestMatchers(HttpMethod.POST, "/api/v1/internship_phases/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/internship_phases/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/internship_phases/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/evaluation_criteria/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR")
                                .requestMatchers(HttpMethod.POST, "/api/v1/evaluation_criteria/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/evaluation_criteria/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/evaluation_criteria/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(
                        exception -> exception
                                .authenticationEntryPoint(jwtEntryPoint)
                                .accessDeniedHandler(accessDenied)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceCustom);
        return provider;
    }
}
