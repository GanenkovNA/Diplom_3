package ru.yandex.praktikum.stellarburgers_test.api.auth;

import io.restassured.response.Response;
import ru.yandex.praktikum.infrastructure.ApiClient;
import ru.yandex.praktikum.stellarburgers.dto.auth.UserAuthorizationRequestDto;
import ru.yandex.praktikum.stellarburgers.dto.auth.UserDto;

public class AuthService {
    protected static final String AUTHORIZATION_PATH = "/api/auth/login";
    protected static final String DELETE_USER_PATH = "/api/auth/user";

    //Авторизация пользователя
    public static Response authorizeUser(UserDto user){
        UserAuthorizationRequestDto userDto = UserAuthorizationRequestDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        return ApiClient.post(AUTHORIZATION_PATH,
                userDto,
                "Авторизация пользователя " + user.getEmail());
    }

    // Удаление пользователя
    public static Response deleteUser (UserDto user) {
        return ApiClient.delete(DELETE_USER_PATH,
                user.getAccessToken(),
                "Удаление пользователя " + user.getEmail()
        );
    }
}
