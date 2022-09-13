package uz.security.security_with_jwt.enums;

import lombok.Getter;

@Getter
public enum Role {
    SUPER_ADMIN,
    ADMIN,
    OTHER;

    public Role checkRole(String role) {
        for (Role value : values()) {
            if (value.name().equalsIgnoreCase(role)) {
                return value;
            }
        }
        throw new RuntimeException("role not found");
    }
}
