package ru.yandex.praktikum.test.burgers.web.user;

import ru.yandex.praktikum.burgers.pom.AuthorizationPom;
import ru.yandex.praktikum.burgers.pom.RegisterPom;
import ru.yandex.praktikum.test.burgers.web.WebBase;

import static io.qameta.allure.Allure.step;

public class UserBaseWeb extends WebBase {

    // Заполнение формы регистрации
    public static void fillOutRegistrationFormAndClick (RegisterPom registerPage, String name, String email, String password){
        step("Заполнение поля имя", () ->
                registerPage.inputName(name)
        );

        step("Заполнение поля email", () ->
                registerPage.inputEmail(email)
        );

        step("Заполнение поля пароль", () ->
                registerPage.inputPassword(password)
        );

        step("Нажатие кнопки \"Зарегистрироваться\"",
                registerPage::clickOnRegisterButton
        );
    }

    // Заполнение формы авторизации
    public static void fillOutAuthorizationFormAndClick(AuthorizationPom authorizationPage, String email, String password){
        step("Заполнение поля email", () ->
                authorizationPage.inputEmail(email)
        );

        step("Заполнение поля пароль", () ->
                authorizationPage.inputPassword(password)
        );

        step("Нажатие кнопки \"Войти\"",
                authorizationPage::clickOnAuthorizationButton
        );
    }
}
