package ru.yandex.praktikum.stellarburgers_test.web.registration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.dto.auth.UserDto;
import ru.yandex.praktikum.stellarburgers.pom.AuthorizationPom;
import ru.yandex.praktikum.stellarburgers.pom.RegisterPom;
import ru.yandex.praktikum.stellarburgers_test.StellarBurgerBase;
import ru.yandex.praktikum.stellarburgers_test.api.auth.AuthBase;

import static ru.yandex.praktikum.infrastructure.allure.CatchTearDownFail.catchTearDownFail;

public class RegisterNewUserTest extends StellarBurgerBase {
    private UserDto testUser;
    private RegisterPom registerPage;
    private final AuthBase userBase = new AuthBase();
    AuthorizationPom authorizationPage;

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
        try{
            authorizationPage.isAuthorizationPageLoaded();
        } catch (Exception e){
            Assert.fail("Не произведён переход на страницу авторизации! Страница не загрузилась!");
        }

        userBase.authorizeTestUser();
        testUser = userBase.getUser();
        Assert.assertNotNull("Не получилось авторизовать пользователя!", testUser.getAccessToken());
    }

    @After
    public void tearDownTest(){
        if (testUser.getAccessToken() != null){
            catchTearDownFail(userBase::deleteTestUser);
        }
    }

}
