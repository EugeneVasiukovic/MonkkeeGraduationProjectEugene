package tests;

import com.codeborne.selenide.Configuration;
import constants.ITestConstants;
import core.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.RegistrationPage;
import steps.LoginSteps;
import steps.RegistrationSteps;
import core.TestListener;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest implements ITestConstants {
    protected RegistrationPage registrationPage;
    protected RegistrationSteps registrationSteps;
    protected LoginPage loginPage;
    protected LoginSteps loginSteps;
    public static String LOGIN = PropertyReader.getProperty("login");
    public static String PASSWORD = PropertyReader.getProperty("password");
    public static String LOGIN_URL = PropertyReader.getProperty("loginUrl");
    public static String REGISTRATION_URL = PropertyReader.getProperty("registrationUrl");


    public void initPages() {
        registrationPage = new RegistrationPage();
        registrationSteps = new RegistrationSteps();
        loginPage = new LoginPage();
        loginSteps = new LoginSteps();
    }

    @BeforeMethod
    public void initTest() {
        log.info("Initializing test environment.");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        options.addArguments("--disable-popup-blocking");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        setWebDriver(driver);

        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        Configuration.headless = false;
        Configuration.browserSize = "1024x768";
        initPages();
    }

    @AfterMethod
    public void endTest() {
        getWebDriver().quit();
        log.info("Ending test.");
    }
}