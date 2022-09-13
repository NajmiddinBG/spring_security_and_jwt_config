package uz.security.security_with_jwt.service.auth;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.security.security_with_jwt.config.security.CustomUserDetails;
import uz.security.security_with_jwt.dto.auth.AuthUserCreateDto;
import uz.security.security_with_jwt.dto.auth.LoginDto;
import uz.security.security_with_jwt.dto.auth.SessionDto;
import uz.security.security_with_jwt.entity.auth.AuthUser;
import uz.security.security_with_jwt.mapper.authUser.AuthUserMapper;
import uz.security.security_with_jwt.repository.authUser.AuthUserRepository;
import uz.security.security_with_jwt.response.AppErrorDto;
import uz.security.security_with_jwt.response.DataDto;
import uz.security.security_with_jwt.response.ResponseEntity;
import uz.security.security_with_jwt.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService, BaseService {
    
    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        });
        return new CustomUserDetails(user);
    }
    
    public Long createUser(AuthUserCreateDto dto) {
        AuthUser user = mapper.fromCreateDto(dto);
        
        return null;
    }

    public ResponseEntity<DataDto<SessionDto>> login(LoginDto dto) {
        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost("http://localhost:9000" + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = client.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new DataDto<>(sessionDto));
            }
            return new ResponseEntity<>(
                    new DataDto<>(objectMapper.readValue(json_auth.get("error").toString(), AppErrorDto.class)));

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .message(e.getLocalizedMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }
    }

    public ResponseEntity<DataDto<SessionDto>> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {
        return null;
    }
}
