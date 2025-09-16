package ra.edu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ra.edu.model.entity.Role;
import ra.edu.repository.RoleRepository;

@SpringBootApplication
public class TplusJv250321Md06Session07Application {

    public static void main(String[] args) {
        SpringApplication.run(TplusJv250321Md06Session07Application.class, args);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("12345"));
    }

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByRoleName("ROLE_USER").isEmpty()) {
                roleRepository.save(Role.builder().roleName("ROLE_USER").build());
            }
        };
    }
}
