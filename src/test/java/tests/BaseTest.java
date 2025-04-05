package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.RegistrationPage;
import steps.LoginSteps;
import steps.RegistrationStep;
import testlisteners.TestListener;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    protected RegistrationPage registrationPage;
    protected RegistrationStep registrationStep;
    protected LoginPage loginPage;
    protected LoginSteps loginSteps;
    public static String LOGIN = PropertyReader.getProperty("login");
    public static String PASSWORD = PropertyReader.getProperty("password");
    public static String LOGIN_URL = PropertyReader.getProperty("loginUrl");
    public static String REGISTRATION_URL = PropertyReader.getProperty("registrationUrl");
    public static String SUCCESS_REGISTRATION_TEXT = PropertyReader.getProperty("successRegistrationText");
    public static String ERROR_REGISTRATION_TEXT = PropertyReader.getProperty("registrationErrorMessage");
    public static String LOGIN_SUCCESFUL_TEXT = PropertyReader.getProperty("loginSuccesfulText");
    public static String ERROR_TEXT_LOGIN_FORM = PropertyReader.getProperty("textMandatoryFild");
    public static String ERROR_TEXT_INCCORECT_FILD_LOGIN_FORM = PropertyReader.getProperty("textErrorInccorectFildLogin");

    public void initPages(){
        registrationPage = new RegistrationPage();
        registrationStep = new RegistrationStep();
        loginPage = new LoginPage();
        loginSteps = new LoginSteps();
    }

    @BeforeMethod
    public  void initTest(){
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
        Configuration.browserSize =  "1024x768";
        initPages();
    }

    @AfterMethod
    public void endTest(){
        getWebDriver().quit();
    }
}