package org.example.infra.security;

import lombok.AllArgsConstructor;
import org.example.domain.User;
import org.example.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsersAutenticatedUtils {

    private final UserRepository userRepository;

    public Long getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();  // UserDetails é o seu User
            return user.getId();
        }
        return null;
    }

}
