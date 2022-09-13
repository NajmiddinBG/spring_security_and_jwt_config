package uz.security.security_with_jwt.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.security.security_with_jwt.dto.base.GenericDto;

@Getter
@Setter
public class AuthUserUpdateDto extends GenericDto {
    private String fullName;

    private String username;

    private String password;

}
