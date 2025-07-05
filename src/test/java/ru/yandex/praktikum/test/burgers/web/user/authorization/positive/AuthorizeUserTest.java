package ru.yandex.praktikum.test.burgers.web.user.authorization.positive;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.burgers.dto.user.UserDto;
import ru.yandex.praktikum.burgers.pom.*;
import ru.yandex.praktikum.test.burgers.api.user.UserBaseApi;
import ru.yandex.praktikum.test.burgers.web.user.UserBaseWeb;

import java.util.Arrays;
import java.util.Collection;

import static io.qameta.allure.Allure.step;
import static ru.yandex.praktikum.infrastructure.allure.CatchTearDownFail.catchTearDownFail;

@DisplayName("Авторизация пользователя")
@RunWith(Parameterized.class)
public class AuthorizeUserTest extends UserBaseWeb {
    private UserDto testUser;
    private final UserBaseApi userBaseApi = new UserBaseApi();

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
                "Вход по кнопке «Войти в аккаунт» на главной",
                "Вход через кнопку «Личный кабинет»",
                "Вход через кнопку в форме регистрации",
                "Вход через кнопку в форме восстановления пароля");
    }

    @Before
    public void setUpTest(){
        userBaseApi.createTestUser();
        userBaseApi.registerTestUser();
        testUser = userBaseApi.getUser();

        constructorPage = new ConstructorPom(driver);
        authorizationPage = new AuthorizationPom(driver);
        profilePage = new ProfilePom(driver);
    }

    @Description("Проверка возможности авторизации из разных начальных точек ")
    @Test
    public void shouldAuthoriseUserTest(){
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName(authMethod));

        switch (authMethod){
            case "Вход по кнопке «Войти в аккаунт» на главной":
                authViaConstructorPage();
                break;
            case "Вход через кнопку «Личный кабинет»":
                authViaHeaderButton();
                break;
            case "Вход через кнопку в форме регистрации":
                authViaRegistrationPage();
                break;
            case "Вход через кнопку в форме восстановления пароля":
                authViaForgottenPasswordPage();
                break;
        }

        step("Проверка перехода на страницу авторизации", () ->
                isPageLoaded(authorizationPage::isPageLoaded, authorizationPage.getPageName())
        );

        //Заполнение формы авторизации
        fillOutAuthorizationFormAndClick(authorizationPage, testUser.getEmail(), testUser.getPassword());

        step("Проверка перехода на домашнюю страницу", () ->
                isPageLoaded(constructorPage::isPageLoaded, constructorPage.getPageName())
        );

        step("Нажатие на кнопку \"Личный кабинет\"", () ->
                constructorPage.clickAccountHeaderButton()
        );

        step("Проверка загрузки страницы личного кабинета", () ->
                isPageLoaded(profilePage::isPageLoaded, profilePage.getPageName())
        );

        step("Проверка данных пользователя в личном кабинете", () -> {
            soft.assertThat(profilePage.getProfileEmail())
                    .as("Проверка указанного в профиле email")
                    .isEqualTo(testUser.getEmail());

            soft.assertThat(profilePage.getProfileName())
                    .as("Проверка указанного в профиле имени")
                    .isEqualTo(testUser.getName());
            soft.assertAll();
        });
    }

    @After
    public void tearDownTest(){
        if (testUser.getAccessToken() != null){
            catchTearDownFail(userBaseApi::deleteTestUser);
        }
    }

    private void authViaConstructorPage(){
        step("Открытие домашней страницы",() -> {
            constructorPage.openPage();
            isPageLoaded(constructorPage::isPageLoaded, constructorPage.getPageName());
        });

        step("Нажатие на кнопку \"Войти в аккаунт\"", () ->
                constructorPage.clickAuthButton()
        );

    }

    private void authViaHeaderButton(){
        step("Открытие домашней страницы",() -> {
            constructorPage.openPage();
            isPageLoaded(constructorPage::isPageLoaded, constructorPage.getPageName());
        });

        step("Нажатие на кнопку \"Личный кабинет\"", () ->
                constructorPage.clickAccountHeaderButton()
        );
    }

    private void authViaRegistrationPage(){
        RegisterPom registerPage = new RegisterPom(driver);

        step("Открытие страницы регистрации",() -> {
            registerPage.openPage();
            isPageLoaded(registerPage::isPageLoaded, registerPage.getPageName());
        });

        step("Нажатие на кнопку \"Войти\"",
                registerPage::clickAuthButton
        );
    }

    private void authViaForgottenPasswordPage(){
        ForgottenPasswordPom forgottenPasswordPage =new ForgottenPasswordPom(driver);

        step("Открытие страницы восстановления пароля",() -> {
            forgottenPasswordPage.openPage();
            isPageLoaded(forgottenPasswordPage::isPageLoaded, forgottenPasswordPage.getPageName());
        });

        step("Нажатие на кнопку \"Войти\"",
                forgottenPasswordPage::clickAuthButton
        );
    }

}
