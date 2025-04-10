package steps;

import io.qameta.allure.Step;
import pages.RegistrationPage;

public class RegistrationSteps {
    RegistrationPage registrationPage;

    public RegistrationSteps() {
        this.registrationPage = new RegistrationPage();
    }

    @Step("Open registration page and check if it's opened")
    private RegistrationPage openAndCheckRegistrationPage(String url) {
        return registrationPage
                .openPage(url)
                .isRegistrationPageOpened();
    }

    @Step("Registration by user: email, password")
    public void registration(String url, String email, String password, String passwordHint) {
        openAndCheckRegistrationPage(url)
                .registration(email, password, passwordHint);
    }

    @Step("Registration by user write no valid email and password")
    public void registrationFormFilling(String url, String email, String password, String passwordHint) {
        openAndCheckRegistrationPage(url)
                .fillRegistrationPageWithoutClick(email, password, passwordHint);
    }

    @Step("Registration by user write valid email and password and unchecked checkbox")
    public void fillRegistrationPageWithoutCheckboxes(String url, String email, String password, String passwordHint) {
        openAndCheckRegistrationPage(url)
                .fillRegistrationPageWithoutCheckboxes(email, password, passwordHint);
    }
}
