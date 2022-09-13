package uz.security.security_with_jwt.controller.auth;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.security.security_with_jwt.controller.base.AbstractController;
import uz.security.security_with_jwt.dto.auth.LoginDto;
import uz.security.security_with_jwt.dto.auth.SessionDto;
import uz.security.security_with_jwt.response.DataDto;
import uz.security.security_with_jwt.response.ResponseEntity;
import uz.security.security_with_jwt.service.auth.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login/")
public class AuthUserController extends AbstractController<AuthService> {

    public AuthUserController(AuthService service) {
        super(service);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> getToken(@RequestBody LoginDto dto) {
        ResponseEntity<DataDto<SessionDto>> token = service.login(dto);
        return token;
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<DataDto<SessionDto>> getToken(HttpServletRequest request, HttpServletResponse response) {
        return service.refreshToken(request, response);
    }

}
