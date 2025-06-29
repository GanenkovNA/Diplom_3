package ru.yandex.praktikum.burgers.dto.user.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthorizationRequestDto {
    private String email;
    private String password;
}