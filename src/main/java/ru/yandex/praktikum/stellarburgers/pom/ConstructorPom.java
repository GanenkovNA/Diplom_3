package ru.yandex.praktikum.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.stellarburgers.BrowserConfig.DEFAULT_WAIT_TIME;

public class ConstructorPom extends HeaderPom{
    private static final String pageName = "Домашняя страница (конструктор)";
    public static final String BASE_URI="https://stellarburgers.nomoreparties.site";

    private static final By ingredientsBlockLocator = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients')]");
    private static final By loginButtonLocator = By.xpath("//button[contains(@class, 'button_button') and contains(text(), 'Войти в аккаунт')]");

    public ConstructorPom(WebDriver driver) {
        super(driver);
    }

    public void isConstructorPageLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientsBlockLocator));
    }

    public void openConstructorPage(){
        driver.get(BASE_URI);
        isConstructorPageLoaded();
    }

    public void clickLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(loginButtonLocator))
                .click();
    }

    public String getPageName(){
        return pageName;
    }
}
