package uz.security.security_with_jwt.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.security.security_with_jwt.entity.base.Auditable;
import uz.security.security_with_jwt.enums.Role;
import uz.security.security_with_jwt.enums.Status;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "username_index", columnList = "username")
})
public class AuthUser extends Auditable {

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;


}
