package ru.yandex.praktikum.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.stellarburgers.BrowserConfig.DEFAULT_WAIT_TIME;

public class ConstructorPom extends HeaderPom{
    private static final By loginButtonLocator = By.xpath("//button[contains(@class, 'button_button') and contains(text(), 'Войти в аккаунт')]");

    public ConstructorPom(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(loginButtonLocator))
                .click();
    }
}
