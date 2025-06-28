package ru.yandex.praktikum.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.stellarburgers.pom.base.HeaderPom;
import ru.yandex.praktikum.stellarburgers.pom.base.PageMethods;

import java.time.Duration;

import static ru.yandex.praktikum.stellarburgers.BrowserConfig.DEFAULT_WAIT_TIME;

public class ProfilePom extends HeaderPom implements PageMethods {
    private static final String pageName = "Личный кабинет";
    public static final String PATH = BASE_URI + "/account/profile";

    private static final By profileBlockLocator = By.xpath("//div[contains(@class, 'Account_contentBox')]");
    private static final By nameInputFieldLocator = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input");
    private static final By emailInputFieldLocator = By.xpath("//label[contains(text(), 'Логин')]/following-sibling::input");

    public ProfilePom(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(profileBlockLocator));
    }

    @Override
    public void openPage() {
        driver.get(PATH);
        isPageLoaded();
    }

    public String getProfileName(){
        return driver.findElement(nameInputFieldLocator)
                .getAttribute("value");
    }

    public String getProfileEmail(){
        return driver.findElement(emailInputFieldLocator)
                .getAttribute("value");
    }

    @Override
    public String getPageName(){
        return pageName;
    }
}
