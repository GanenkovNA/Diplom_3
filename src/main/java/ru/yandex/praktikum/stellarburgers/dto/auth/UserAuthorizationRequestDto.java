package ru.yandex.praktikum.stellarburgers.dto.auth;

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