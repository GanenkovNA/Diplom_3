package ru.yandex.praktikum.stellarburgers_test.web.registration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.dto.auth.UserDto;
import ru.yandex.praktikum.stellarburgers.pom.AuthorizationPom;
import ru.yandex.praktikum.stellarburgers.pom.RegisterPom;
import ru.yandex.praktikum.stellarburgers_test.api.auth.AuthBase;
import ru.yandex.praktikum.stellarburgers_test.web.WebBase;

import static ru.yandex.praktikum.infrastructure.allure.CatchTearDownFail.catchTearDownFail;

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

    @Test
    public void shouldRegisterUser(){
        registerPage.openRegisterPage();
        registerPage.fillOutRegistrationFormAndClick(testUser.getName(), testUser.getEmail(), testUser.getPassword());
        isPageLoaded(authorizationPage::isAuthorizationPageLoaded, authorizationPage.getPageName());

        userBase.authorizeTestUser();
        testUser = userBase.getUser();
        Assert.assertNotNull("Не получилось авторизовать пользователя!\nНе был получен accessToken через API", testUser.getAccessToken());
    }

    @After
    public void tearDownTest(){
        if (testUser.getAccessToken() != null){
            catchTearDownFail(userBase::deleteTestUser);
        }
    }
}
