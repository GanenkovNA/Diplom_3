package ru.yandex.praktikum.test.burgers.web.user.registration.positive;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.burgers.dto.user.UserDto;
import ru.yandex.praktikum.burgers.pom.AuthorizationPom;
import ru.yandex.praktikum.burgers.pom.RegisterPom;
import ru.yandex.praktikum.test.burgers.api.user.UserBaseApi;
import ru.yandex.praktikum.test.burgers.web.user.UserBaseWeb;

import static io.qameta.allure.Allure.step;
import static ru.yandex.praktikum.infrastructure.allure.CatchTearDownFail.catchTearDownFail;

@DisplayName("Регистрация нового пользователя")
public class RegisterNewUserTest extends UserBaseWeb {
    private UserDto testUser;
    private RegisterPom registerPage;
    private AuthorizationPom authorizationPage;
    private final UserBaseApi userBaseApi = new UserBaseApi();

    @Before
    public void setUpTest(){
        userBaseApi.createTestUser();
        testUser = userBaseApi.getUser();

        registerPage = new RegisterPom(driver);
        authorizationPage = new AuthorizationPom(driver);
        System.out.println(testUser.toString());
    }

    @DisplayName("Регистрация нового пользователя")
    @Description("Проводится: \n" +
            "* регистрация нового пользователя через web-интерфейс \n" +
            "* проверка его создания через API")
    @Test
    public void shouldRegisterUserTest(){
        step("Открытие страницы регистрации",() -> {
            registerPage.openPage();
            registerPage.isPageLoaded();
        });

        // Заполнение формы регистрации
        fillOutRegistrationFormAndClick(registerPage, testUser.getName(), testUser.getEmail(), testUser.getPassword());

        step("Проверка перехода на страницу авторизации", () ->
            isPageLoaded(authorizationPage::isPageLoaded, authorizationPage.getPageName())
        );

        step("Авторизация нового пользователя через API", () -> {
            userBaseApi.authorizeTestUser();
            testUser = userBaseApi.getUser();
            Assert.assertNotNull("Не получилось авторизовать пользователя!\nНе был получен accessToken через API", testUser.getAccessToken());
        });
    }

    @After
    public void tearDownTest(){
        if (testUser.getAccessToken() != null){
            catchTearDownFail(userBaseApi::deleteTestUser);
        }
    }
}
