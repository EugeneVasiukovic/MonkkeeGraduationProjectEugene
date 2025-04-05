package steps;

import io.qameta.allure.Step;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Step("Login user: login and password")
    public void login(String url, String login, String password) {
        loginPage
                .openPage(url)
                .isPageOpened()
                .login(login, password);
    }
}
