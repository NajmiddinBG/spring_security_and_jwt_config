package uz.security.security_with_jwt.config.security;

import com.google.firebase.database.annotations.NotNull;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.security.security_with_jwt.entity.auth.AuthUser;
import uz.security.security_with_jwt.enums.Status;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {
    
    private Long id;
    private String username;
    private String password;
    private Status status;
    private boolean deleted;
    private Set<GrantedAuthority> authorities;
    
    public CustomUserDetails(@NotNull final AuthUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.deleted = user.isDeleted();
        this.authorities = new HashSet<>() {{
            add(new SimpleGrantedAuthority(user.getRole().toString()));
        }};
        
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
        return this.status != Status.BLOCKED && !this.deleted;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return this.status != Status.BLOCKED;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return this.status != Status.BLOCKED && !this.deleted;
    }
}
