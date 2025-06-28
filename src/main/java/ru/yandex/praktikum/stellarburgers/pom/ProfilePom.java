package ru.yandex.praktikum.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.stellarburgers.BrowserConfig.DEFAULT_WAIT_TIME;

public class ProfilePom extends ConstructorPom{
    private static final String pageName = "Личный кабинет";
    public static final String PROFILE_PATH = BASE_URI + "/account/profile";

    private static final By profileBlockLocator = By.xpath("//div[contains(@class, 'Account_contentBox')]");
    private static final By nameInputFieldLocator = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input");
    private static final By emailInputFieldLocator = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input");

    public ProfilePom(WebDriver driver) {
        super(driver);
    }

    public void isProfilePageLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(profileBlockLocator));
    }

    public void openProfilePage(){
        driver.get(PROFILE_PATH);
        isProfilePageLoaded();
    }

    public String getProfileName(){
        return driver.findElement(nameInputFieldLocator)
                .getAttribute("value");
    }

    public String getProfileEmail(){
        return driver.findElement(emailInputFieldLocator)
                .getAttribute("value");
    }

    public String getPageName(){
        return pageName;
    }
}
