package ru.yandex.praktikum.burgers.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
@With
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String accessToken;
    private String refreshToken;
}