package ru.yandex.praktikum.infrastructure.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static ru.yandex.praktikum.burgers.browser.BrowserConfig.DEFAULT_IMPLICIT_WAIT;

public class DriverFactory {
    private static final String YANDEX_PATH = "C:\\Users\\Kit\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
    private static final String YANDEX_DRIVER_PATH = "C:\\Users\\Kit\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\yandexdriver.exe";

    public static WebDriver getDriver(Browser browserName) {
        WebDriver driver;

        switch (browserName) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            case YANDEX:
                //Здесь должна быть настройка
                System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary(YANDEX_PATH);
                yandexOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(yandexOptions);
                break;
            default:
                throw new IllegalArgumentException("Unknown browser: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_IMPLICIT_WAIT));

        return driver;
    }

    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}