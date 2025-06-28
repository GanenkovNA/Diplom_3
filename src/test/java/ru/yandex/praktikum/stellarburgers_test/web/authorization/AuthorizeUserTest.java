package ru.yandex.praktikum.stellarburgers_test.web.authorization;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.stellarburgers.dto.auth.UserDto;
import ru.yandex.praktikum.stellarburgers.pom.*;
import ru.yandex.praktikum.stellarburgers_test.api.auth.AuthBase;
import ru.yandex.praktikum.stellarburgers_test.web.WebBase;

import java.util.Arrays;
import java.util.Collection;

import static ru.yandex.praktikum.infrastructure.allure.CatchTearDownFail.catchTearDownFail;

@RunWith(Parameterized.class)
public class AuthorizeUserTest extends WebBase {
    private UserDto testUser;
    private final AuthBase userBase = new AuthBase();

    private AuthorizationPom authorizationPage;
    private ConstructorPom constructorPage;
    private ProfilePom profilePage;

    private final String authMethod;
    private final SoftAssertions soft = new SoftAssertions();

    public AuthorizeUserTest(String authMethod) {
        this.authMethod = authMethod;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String> data() {
        return Arrays.asList(
                "вход по кнопке «Войти в аккаунт» на главной",
                "вход через кнопку «Личный кабинет»",
                "вход через кнопку в форме регистрации",
                "вход через кнопку в форме восстановления пароля");
    }

    @Before
    public void setUpTest(){
        userBase.createTestUser();
        userBase.registerTestUser();
        testUser = userBase.getUser();

        constructorPage = new ConstructorPom(driver);
        authorizationPage = new AuthorizationPom(driver);
        profilePage = new ProfilePom(driver);
    }

    @Test
    public void shouldAuthoriseUser(){
        switch (authMethod){
            case "вход по кнопке «Войти в аккаунт» на главной":
                authViaConstructorPage();
                break;
            case "вход через кнопку «Личный кабинет»":
                authViaHeaderButton();
                break;
            case "вход через кнопку в форме регистрации":
                authViaRegistrationPage();
                break;
            case "вход через кнопку в форме восстановления пароля":
                authViaForgottenPasswordPage();
                break;
        }

        isPageLoaded(authorizationPage::isPageLoaded, authorizationPage.getPageName());
        authorizationPage.fillOutAuthorizationFormAndClick(testUser.getEmail(), testUser.getPassword());

        isPageLoaded(constructorPage::isPageLoaded, constructorPage.getPageName());
        constructorPage.clickAccountHeaderButton();

        isPageLoaded(profilePage::isPageLoaded, profilePage.getPageName());

        soft.assertThat(profilePage.getProfileEmail())
                .as("Проверка указанного в профиле email")
                .isEqualTo(testUser.getEmail());

        soft.assertThat(profilePage.getProfileName())
                .as("Проверка указанного в профиле имени")
                .isEqualTo(testUser.getName());
        soft.assertAll();
    }

    @After
    public void tearDownTest(){
        if (testUser.getAccessToken() != null){
            catchTearDownFail(userBase::deleteTestUser);
        }
    }

    private void authViaConstructorPage(){
        constructorPage.openPage();
        isPageLoaded(constructorPage::isPageLoaded, constructorPage.getPageName());
        constructorPage.clickAuthButton();
    }

    private void authViaHeaderButton(){
        constructorPage.openPage();
        isPageLoaded(constructorPage::isPageLoaded, constructorPage.getPageName());
        constructorPage.clickAccountHeaderButton();
    }

    private void authViaRegistrationPage(){
        RegisterPom registerPage = new RegisterPom(driver);
        registerPage.openPage();
        isPageLoaded(registerPage::isPageLoaded, constructorPage.getPageName());
        registerPage.clickAuthButton();
    }

    private void authViaForgottenPasswordPage(){
        ForgottenPasswordPom forgottenPasswordPage =new ForgottenPasswordPom(driver);
        forgottenPasswordPage.openPage();
        isPageLoaded(forgottenPasswordPage::isPageLoaded, forgottenPasswordPage.getPageName());
        forgottenPasswordPage.clickAuthButton();
    }

}
