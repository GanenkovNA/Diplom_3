package ru.yandex.praktikum.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.stellarburgers.BrowserConfig.DEFAULT_WAIT_TIME;

public class HeaderPom {
    public static final String BASE_URI="https://stellarburgers.nomoreparties.site";
    protected final WebDriver driver;

    //Локаторы элементов шапки
    protected static final By constructorButtonLocator = By.xpath("//a[contains(@class, 'AppHeader_header_') and @href='/']");
    protected static final By orderFeedButtonLocator = By.xpath("//a[contains(@class, 'AppHeader_header_') and @href='/feed']");
    protected static final By logoLocator = By.xpath("//div[contains(@class, 'AppHeader_header_')]");
    protected static final By accountButtonLocator = By.xpath("//a[contains(@class, 'AppHeader_header_') and @href='/account']");

    public HeaderPom(WebDriver driver) {
        this.driver = driver;
    }

    public void isMainPageLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(logoLocator));
    }

    public void openMainPage(){
        driver.get(BASE_URI);
        isMainPageLoaded();
    }

    public void clickConstructorButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(constructorButtonLocator))
                .click();
    }

    public void clickOrderFeedButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(orderFeedButtonLocator))
                .click();
    }

    public void clickAccountButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(accountButtonLocator))
                .click();
    }
}
