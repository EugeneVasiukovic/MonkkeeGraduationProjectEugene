package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Checkbox;
import elements.Input;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RegistrationPage extends BasePage {
    private static final SelenideElement BUTTON_OK = $x("//button[contains(@class, 'btn') and contains(text(), 'OK')]");
    private static final SelenideElement ERROR_MESSAGE = $x("//div[contains(@class, 'alert')]");
    private static final SelenideElement SUCCESS_MESSAGE = $x("//div[contains(@class, 'content-container')]");

    /**
     * Opens the registration page.
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
     * @return The RegistrationPage object.
     */
    public RegistrationPage isOpenPage() {
        log.info("Checking if registration page is open.");
        BUTTON_OK.shouldBe(Condition.visible);
        return this;
    }

    /**
     * Fills the registration form with the provided details.
     * @param email The email address.
     * @param password The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    private RegistrationPage fillRegistrationForm(String email, String password, String passwordHint) {
        log.info("Filling registration form with email: " + email + ", password: " + password + ", password hint: " + passwordHint);
        new Input("registration_email").write(email);
        new Input("registration_password").write(password);
        new Input("registration_password_confirmation").write(password);
        new Input("registration_password_hint").write(passwordHint);
        new Checkbox("registration_terms_of_use").checkboxClick();
        new Checkbox("registration_lost_password_warning_registered").checkboxClick();
        return this;
    }

    /**
     * Completes the registration process by filling the form and clicking the OK button.
     * @param email The email address.
     * @param password The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    public RegistrationPage registration(String email, String password, String passwordHint) {
        fillRegistrationForm(email, password, passwordHint);
        log.info("Clicking OK button to complete registration.");
        new Button().click(BUTTON_OK);
        sleep(3000);
        return this;
    }

    /**
     * Fills the registration form without clicking the OK button.
     * @param email The email address.
     * @param password The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    public RegistrationPage fillRegistrationPageWithoutClick(String email, String password, String passwordHint) {
        log.info("Filling registration form without clicking OK button.");
        new Input("registration_email").write(email);
        new Input("registration_password").write(password);
        new Input("registration_password_confirmation").write(password);
        new Input("registration_password_hint").write(passwordHint);
        new Checkbox("registration_terms_of_use").checkboxClick();
        new Checkbox("registration_lost_password_warning_registered").checkboxClick();
        return this;
    }

    /**
     * Fills the registration form without clicking the checkboxes.
     * @param email The email address.
     * @param password The password.
     * @param passwordHint The password hint.
     * @return The RegistrationPage object.
     */
    public RegistrationPage fillRegistrationPageWithoutCheckboxes(String email, String password, String passwordHint) {
        log.info("Filling registration form without clicking checkboxes.");
        new Input("registration_email").write(email);
        new Input("registration_password").write(password);
        new Input("registration_password_confirmation").write(password);
        new Input("registration_password_hint").write(passwordHint);
        new Button().click(BUTTON_OK);
        sleep(3000);
        return this;
    }

    /**
     * Checks if the OK button is disabled.
     * @return True if the OK button is disabled, false otherwise.
     */
    public boolean isOkButtonDisabled() {
        log.info("Checking if OK button is disabled.");
        return !BUTTON_OK.isEnabled();
    }

    /**
     * Gets the error message displayed on the registration page.
     * @return The error message.
     */
    public String getErrorMessage() {
        log.info("Getting error message from registration page.");
        return ERROR_MESSAGE.getText();
    }

    /**
     * Checks if the registration was successful by getting the success message.
     * @return The success message.
     */
    public String isRegistrationSuccessful() {
        log.info("Checking if registration was successful.");
        return SUCCESS_MESSAGE.shouldBe(Condition.visible).getText();
    }
}
