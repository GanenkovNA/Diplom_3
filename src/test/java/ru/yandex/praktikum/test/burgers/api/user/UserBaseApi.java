package ru.yandex.praktikum.test.burgers.api.user;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import net.datafaker.Faker;
import ru.yandex.praktikum.burgers.dto.user.auth.UserAuthorizationResponseDto;
import ru.yandex.praktikum.burgers.dto.user.UserDto;
import ru.yandex.praktikum.burgers.dto.user.register.UserRegistrationResponseDto;
import ru.yandex.praktikum.test.burgers.StellarBurgerBase;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.yandex.praktikum.test.burgers.api.user.UserServiceApi.*;

public class UserBaseApi extends StellarBurgerBase {
    private UserDto testUser;
    private final Faker faker = new Faker();

    @Step("Создание тестового пользователя")
    public void createTestUser(){
        testUser = UserDto.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .name(faker.name().firstName())
                .build();

        Allure.addAttachment("Информация о пользователе", "text/plain",
                "Email: " + testUser.getEmail() + "\nPassword: " + testUser.getPassword());
    }

    @Step("Регистрация тестового пользователя")
    public void registerTestUser(){
        Response response = registerUser(testUser);
        response.then().statusCode(SC_OK);

        UserRegistrationResponseDto responseBody = response.as(UserRegistrationResponseDto.class);
        testUser.setAccessToken(responseBody.getAccessToken());
        testUser.setRefreshToken(responseBody.getRefreshToken());
    }

    @Step("Авторизация тестового пользователя")
    public void authorizeTestUser(){
        Response response = authorizeUser(testUser);
        response.then().statusCode(SC_OK);

        UserAuthorizationResponseDto responseBody = response.as(UserAuthorizationResponseDto.class);
        testUser.setAccessToken(responseBody.getAccessToken());
        testUser.setRefreshToken(responseBody.getRefreshToken());
    }

    @Step("Удаление тестового пользователя")
    public void deleteTestUser() {
        Response response = deleteUser(testUser);
        response.then().statusCode(SC_ACCEPTED);
    }

    public UserDto getUser(){
        return testUser;
    }
}
