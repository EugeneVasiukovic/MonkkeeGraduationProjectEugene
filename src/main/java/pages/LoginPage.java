package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


@Log4j2
public class LoginPage extends BasePage {
    private static final SelenideElement BUTTON_CREATE_POST = $(By.id("create-entry"));
    private static final SelenideElement BUTTON_LOGIN = $x("//*[@type='submit']");
    private static final SelenideElement LOGIN_ERROR_MESSAGE = $x("//input[@name='login']/following-sibling::div[contains(@class, 'help-block')]");
    private static final SelenideElement PASSWORD_ERROR_MESSAGE = $x("//div[@class='password-toggle-wrapper']/following-sibling::div[contains(@class, 'help-block')]");
    private static final SelenideElement LOGIN_FAILED_MESSAGE = $x("//div[contains(@class, 'alert') and contains(text(), 'Login failed')]");

    /**
     * Opens the login page.
     *
     * @param url The URL of the login page.
     * @return The LoginPage object.
     */
    public LoginPage openPage(String url) {
        log.info("Opening login page: " + url);
        open(url);
        return this;
    }

    /**
     * Checks if the login page is open.
     *
     * @return The LoginPage object.
     */
    public LoginPage isPageOpened() {
        log.info("Checking if login page is open.");
        BUTTON_LOGIN.shouldBe(Condition.visible);
        return this;
    }

    /**
     * Fills the login form with the provided details.
     *
     * @param login    The login (email) address.
     * @param password The password.
     * @return The LoginPage object.
     */
    private LoginPage fillLoginForm(String login, String password) {
        log.info("Filling login form with login and password ");
        new Input("login").write(login);
        new Input("password").write(password);
        return this;
    }

    /**
     * Completes the login process by filling the form and clicking the Login button.
     *
     * @param email    The email address.
     * @param password The password.
     * @return The LoginPage object.
     */
    public LoginPage login(String email, String password) {
        fillLoginForm(email, password);
        log.info("Clicking Login button to complete login.");
        new Button().click(BUTTON_LOGIN);
        return this;
    }

    /**
     * Checks if the blog page is opened.
     *
     * @return The text of the blog page header.
     */
    public SelenideElement isBlogPageOpened() {
        log.info("Checking if blog page is opened.");
        return BUTTON_CREATE_POST.shouldBe(Condition.visible);
    }

    /**
     * Gets the error message displayed under the login field.
     *
     * @return The error message.
     */
    public String getLoginFieldErrorMessage() {
        try {
            log.info("Getting error message from login field.");
            return LOGIN_ERROR_MESSAGE.getText();
        } catch (Exception e) {
            log.error("Failed to get login field error message.", e);
            return "";
        }
    }

    /**
     * Gets the error message displayed under the password field.
     *
     * @return The error message.
     */
    public String getPasswordErrorMessage() {
        log.info("Getting error message from password field.");
        return PASSWORD_ERROR_MESSAGE.getText();
    }

    /**
     * Gets the login failed message displayed on the login page.
     *
     * @return The login failed message.
     */
    public String getLoginFormFailedMessage() {
        log.info("Getting login failed message from login page.");
        return LOGIN_FAILED_MESSAGE.getText();
    }
}
