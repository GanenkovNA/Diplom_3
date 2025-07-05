package ru.yandex.praktikum.test.burgers.api.user;

import io.restassured.response.Response;
import ru.yandex.praktikum.infrastructure.rest.ApiClient;
import ru.yandex.praktikum.burgers.dto.user.auth.UserAuthorizationRequestDto;
import ru.yandex.praktikum.burgers.dto.user.UserDto;
import ru.yandex.praktikum.burgers.dto.user.register.UserRegistrationRequestDto;

public class UserServiceApi {
    protected static final String REGISTER_PATH = "/api/auth/register";
    protected static final String AUTHORIZATION_PATH = "/api/auth/login";
    protected static final String DELETE_USER_PATH = "/api/auth/user";

    // Регистрация пользователя
    public static Response registerUser (UserDto user) {
        UserRegistrationRequestDto userData = UserRegistrationRequestDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .build();

        return ApiClient.post(REGISTER_PATH,
                userData,
                "Регистрация пользователя " + user.getEmail()
        );
    }

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
