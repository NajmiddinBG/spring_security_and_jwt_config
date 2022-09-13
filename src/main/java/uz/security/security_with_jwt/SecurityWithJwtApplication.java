package uz.security.security_with_jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecurityWithJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityWithJwtApplication.class, args);
    }

}
