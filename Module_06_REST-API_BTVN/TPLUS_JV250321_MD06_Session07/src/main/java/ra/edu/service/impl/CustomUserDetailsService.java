package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.User;
import ra.edu.model.entity.UserPrincipal;
import ra.edu.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

//    private static final Map<String, String> users = new HashMap<>();
//    static {
//        users.put("alice", "$2a$10$4irXnZyia9cFIfzVCcbkJ.FyztR1.4AMaQBMQWssbnFWuviYozk8a");
//        users.put("bob", "$2a$10$OxRj5yXBL/3sPpHuhZKxYe5Q59m8jGdIR/UhyGfrd1VQzv8ZIdpeC");
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }
}
