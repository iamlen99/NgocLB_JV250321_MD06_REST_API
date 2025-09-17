package ra.edu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.edu.security.jwt.AccessDenied;
import ra.edu.security.jwt.JwtEntryPoint;
import ra.edu.security.jwt.JwtAuthenticationFilter;
import ra.edu.security.principal.UserDetailServiceCustom;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailServiceCustom userDetailServiceCustom)
            throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailServiceCustom).passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtTokenFilter,
                                                   JwtEntryPoint jwtEntryPoint,
                                                   AccessDenied accessDenied,
                                                   AuthenticationManager authenticationManager) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/v1/auths/**").permitAll()
                        .requestMatchers("/v1/admins/**").hasRole("ADMIN")
                        .requestMatchers("/v1/editors/**").hasAnyRole("ADMIN", "EDITOR")
                        .requestMatchers("/v1/users/**").hasAnyRole("ADMIN", "EDITOR", "USER")
                        .requestMatchers("/v1/public/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationManager(authenticationManager)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtEntryPoint)
                        .accessDeniedHandler(accessDenied))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
