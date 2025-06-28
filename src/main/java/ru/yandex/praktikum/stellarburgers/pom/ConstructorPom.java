package ru.yandex.praktikum.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.stellarburgers.pom.base.HeaderPom;
import ru.yandex.praktikum.stellarburgers.pom.base.PageMethods;

import java.time.Duration;

import static ru.yandex.praktikum.stellarburgers.browser.BrowserConfig.DEFAULT_WAIT_TIME;

public class ConstructorPom extends HeaderPom implements PageMethods {
    private static final String pageName = "Домашняя страница (конструктор)";
    private static final String PATH = BASE_URI;

    private static final By ingredientsBlockLocator = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients')]");
    private static final By authButtonLocator = By.xpath("//button[contains(@class, 'button_button') and contains(text(), 'Войти в аккаунт')]");

    public ConstructorPom(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientsBlockLocator));
    }

    @Override
    public void openPage() {
        driver.get(PATH);
        isPageLoaded();
    }

    public void clickAuthButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(authButtonLocator))
                .click();
    }

    @Override
    public String getPageName(){
        return pageName;
    }
}
