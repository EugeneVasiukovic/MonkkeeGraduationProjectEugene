package pages;

import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Checkbox;
import elements.Input;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RegistrationPage extends BasePage {
    private static final SelenideElement BUTTON_OK = $x("//*[@type='submit']");
    private static final SelenideElement ERROR_MESSAGE = $x("//div[contains(@class, 'alert')]");
    private static final SelenideElement SUCCESS_MESSAGE = $x("//div[contains(@class, 'content-container')]");

    /**
     * Opens the registration page.
     *
     * @param url The URL of the registration page.
     * @return The RegistrationPage object.
     */
    public RegistrationPage openPage(String url) {
        log.info("Opening registration page: " + url);
        open(url);
        return this;
    }

    /**
     * Checks if the registration page is open.
     *
     * @return The RegistrationPage object.
     */
    public RegistrationPage isRegistrationPageOpened() {
        log.info("Checking if registration page is open.");
        BUTTON_OK.shouldBe(visible);
        return this;
    }

    /**
     * Fills the registration form with the provided details.
     *
     * @param email        The email address.
     * @param password     The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    private RegistrationPage fillRegistrationForm(String email, String password, String passwordHint) {
        log.info("Filling registration form with email, password, password hint.");
        new Input("registration_email").write(email);
        new Input("registration_password").write(password);
        new Input("registration_password_confirmation").write(password);
        new Input("registration_password_hint").write(passwordHint);
        return this;
    }

    /**
     * Completes the registration process by filling the form and clicking the OK button.
     *
     * @param email        The email address.
     * @param password     The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    public RegistrationPage registration(String email, String password, String passwordHint) {
        fillRegistrationForm(email, password, passwordHint);
        new Checkbox("registration_terms_of_use").setCheckboxValue(true);
        new Checkbox("registration_lost_password_warning_registered").setCheckboxValue(true);
        log.info("Clicking OK button to complete registration.");
        new Button().click(BUTTON_OK);
        SUCCESS_MESSAGE.shouldHave(text("User registered"), Duration.ofSeconds(10));
        return this;
    }

    /**
     * Fills the registration form without clicking the OK button.
     *
     * @param email        The email address.
     * @param password     The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    public RegistrationPage fillRegistrationPageWithoutClick(String email, String password, String passwordHint) {
        log.info("Filling registration form without clicking OK button.");
        fillRegistrationForm(email, password, passwordHint);
        new Checkbox("registration_terms_of_use").setCheckboxValue(false);
        new Checkbox("registration_lost_password_warning_registered").setCheckboxValue(false);
        return this;
    }

    /**
     * Fills the registration form without clicking the checkboxes.
     *
     * @param email        The email address.
     * @param password     The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    public RegistrationPage fillRegistrationPageWithoutCheckboxes(String email, String password, String passwordHint) {
        log.info("Filling registration form without clicking checkboxes.");
        fillRegistrationForm(email, password, passwordHint);
        new Button().click(BUTTON_OK);
        ERROR_MESSAGE.shouldHave(text("Registration not successful"), Duration.ofSeconds(10));
        return this;
    }

    /**
     * Checks if the OK button is disabled.
     *
     * @return True if the OK button is disabled, false otherwise.
     */
    public boolean isOkButtonDisabled() {
        log.info("Checking if OK button is disabled.");
        return !BUTTON_OK.isEnabled();
    }

    /**
     * Gets the error or success message displayed on the registration page.
     *
     * @param isError If true, retrieves the error message; otherwise, retrieves the success message.
     * @return The message.
     */
    public String getRegistrationMessage(boolean isError) {
        log.info("Getting registration message. Is error: " + isError);
        if (isError) {
            return ERROR_MESSAGE.getText();
        } else {
            return SUCCESS_MESSAGE.shouldBe(visible).getText();
        }
    }

}
