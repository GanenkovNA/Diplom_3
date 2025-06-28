package ru.yandex.praktikum.stellarburgers_test.web.registration.negative;

import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.stellarburgers.dto.auth.UserDto;
import ru.yandex.praktikum.stellarburgers.pom.RegisterPom;
import ru.yandex.praktikum.stellarburgers_test.web.WebBase;

import java.util.Arrays;
import java.util.Collection;

@DisplayName("Регистрация пользователя с паролем менее 6 символов")
@RunWith(Parameterized.class)
public class RegisterUserWithSmallPasswordTest extends WebBase {
    private final Faker faker = new Faker();
    private UserDto userDto;
    private RegisterPom registerPage;
    private final int passwordLength;

    public RegisterUserWithSmallPasswordTest(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    @Parameterized.Parameters
    public static Collection<Integer> data() {
        return Arrays.asList(1, 4, 5);
    }

    @Before
    public void setUpTest(){
        userDto = UserDto.builder()
                .name(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(passwordLength, passwordLength))
                .build();
        registerPage = new RegisterPom(driver);
    }

    @Test
    public void shouldDisplayPasswordError(){
        registerPage.openRegisterPage();
        isPageLoaded(registerPage::isRegisterPageLoaded, registerPage.getPageName());

        registerPage.fillOutRegistrationFormAndClick(userDto.getName(), userDto.getEmail(), userDto.getPassword());

        Assert.assertTrue("Не отобразилась ошибка о некорректном пароле!", registerPage.isPasswordErrorDisplayed());
    }
}
