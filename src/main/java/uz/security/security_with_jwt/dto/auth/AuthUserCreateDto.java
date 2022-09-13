package uz.security.security_with_jwt.dto.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.security.security_with_jwt.dto.base.BaseDto;

@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthUserCreateDto implements BaseDto {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
}
