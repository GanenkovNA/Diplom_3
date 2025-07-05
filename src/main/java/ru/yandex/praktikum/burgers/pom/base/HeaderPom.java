package ru.yandex.praktikum.burgers.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.burgers.browser.BrowserConfig.DEFAULT_WAIT_TIME;

public class HeaderPom {
    protected final WebDriver driver;
    public static final String BASE_URI="https://stellarburgers.nomoreparties.site";

    //Локаторы элементов шапки
    protected static final By constructorHeaderButtonLocator = By.xpath("//a[contains(@class, 'AppHeader_header_') and @href='/']");
    protected static final By orderFeedHeaderButtonLocator = By.xpath("//a[contains(@class, 'AppHeader_header_') and @href='/feed']");
    protected static final By logoLocator = By.xpath("//div[contains(@class, 'AppHeader_header_')]");
    protected static final By accountHeaderButtonLocator = By.xpath("//a[contains(@class, 'AppHeader_header_') and @href='/account']");

    public HeaderPom(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConstructorHeaderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(constructorHeaderButtonLocator))
                .click();
    }

    public void clickOrderFeedHeaderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(orderFeedHeaderButtonLocator))
                .click();
    }

    public void clickAccountHeaderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(accountHeaderButtonLocator))
                .click();
    }
}
