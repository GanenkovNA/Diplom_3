package ru.yandex.praktikum.stellarburgers_test.web.registration;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.dto.auth.UserDto;
import ru.yandex.praktikum.stellarburgers.pom.AuthorizationPom;
import ru.yandex.praktikum.stellarburgers.pom.RegisterPom;
import ru.yandex.praktikum.stellarburgers_test.api.auth.AuthBase;
import ru.yandex.praktikum.stellarburgers_test.web.WebBase;

import static io.qameta.allure.Allure.step;
import static ru.yandex.praktikum.infrastructure.allure.CatchTearDownFail.catchTearDownFail;

@DisplayName("Регистрация нового пользователя")
public class RegisterNewUserTest extends WebBase {
    private UserDto testUser;
    private RegisterPom registerPage;
    private AuthorizationPom authorizationPage;
    private final AuthBase userBase = new AuthBase();

    @Before
    public void setUpTest(){
        userBase.createTestUser();
        testUser = userBase.getUser();

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

        step("Заполнение формы регистрации и переход на страницу авторизации", () ->
            registerPage.fillOutRegistrationFormAndClick(testUser.getName(), testUser.getEmail(), testUser.getPassword())
        );
        step("Проверка перехода на страницу авторизации", () ->
            isPageLoaded(authorizationPage::isPageLoaded, authorizationPage.getPageName())
        );

        step("Авторизация нового пользователя через API", () -> {
            userBase.authorizeTestUser();
            testUser = userBase.getUser();
            Assert.assertNotNull("Не получилось авторизовать пользователя!\nНе был получен accessToken через API", testUser.getAccessToken());
        });
    }

    @After
    public void tearDownTest(){
        if (testUser.getAccessToken() != null){
            catchTearDownFail(userBase::deleteTestUser);
        }
    }
}
