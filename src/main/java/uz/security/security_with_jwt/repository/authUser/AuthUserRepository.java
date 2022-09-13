package uz.security.security_with_jwt.repository.authUser;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.security.security_with_jwt.entity.auth.AuthUser;
import uz.security.security_with_jwt.repository.base.BaseRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {
    
    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);
    
    Optional<AuthUser> findByUsername(String phoneNumber);
}
