package uz.security.security_with_jwt.session;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.security.security_with_jwt.entity.auth.AuthUser;
import uz.security.security_with_jwt.enums.Role;
import uz.security.security_with_jwt.enums.Status;
import uz.security.security_with_jwt.repository.authUser.AuthUserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionUser {

    private final AuthUserRepository repository;


    public String getUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Long getId() {
        Optional<AuthUser> user = repository.findByUsernameAndDeletedFalse(this.getUsername());
        return user.get().getId();
    }


    public Role getRole() {
        Optional<AuthUser> user = repository.findByUsernameAndDeletedFalse(this.getUsername());
        return user.get().getRole();
    }


    public Status getStatus() {
        Optional<AuthUser> user = repository.findByUsernameAndDeletedFalse(this.getUsername());
        return user.get().getStatus();
    }

}
