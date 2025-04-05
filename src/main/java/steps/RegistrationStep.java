package steps;

import io.qameta.allure.Step;
import pages.RegistrationPage;

public class RegistrationStep {
    RegistrationPage registrationPage;

    public RegistrationStep() {
        this.registrationPage = new RegistrationPage();
    }

    @Step("Registration by user: email, password")
    public void registration(String url, String email, String password, String passwrodHint){
        registrationPage
                .openPage(url)
                .isOpenPage()
                .registration(email,password,passwrodHint);
    }

    @Step("Registration by user write no valid email and password")
    public void registrationFormFillingTest(String url, String email, String password, String passwrodHint){
        registrationPage
                .openPage(url)
                .isOpenPage()
                .fillRegistrationPageWithoutClick(email,password,passwrodHint);
    }

    @Step("Registration by user write valid email and password and unchecked checkbox ")
    public void fillRegistrationPageWithoutCheckboxes(String url, String email, String password, String passwrodHint){
        registrationPage
                .openPage(url)
                .isOpenPage()
                .fillRegistrationPageWithoutCheckboxes(email,password,passwrodHint);
    }
}