package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(description = "Login successful valid login and password",
            retryAnalyzer = Retry.class)
    public void loginSuccessfulTest(){
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        Assert.assertEquals(loginPage.isBlogPageOpened(),LOGIN_SUCCESFUL_TEXT);
    }

    @Test(description = "Login not successful, empty login, write password",
            retryAnalyzer = Retry.class)
    public void loginSendEmptyLoginTest(){
        loginSteps
                .login(LOGIN_URL, "", PASSWORD);
        Assert.assertEquals(loginPage.getLoginFildErrorMessage(),ERROR_TEXT_LOGIN_FORM);
    }

    @Test(description = "Login not successful write login,empty password",
            retryAnalyzer = Retry.class)
    public void loginSendEmptyPasswordTest(){
        loginSteps
                .login(LOGIN_URL, LOGIN, "");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(),ERROR_TEXT_LOGIN_FORM);
    }

    @Test(description = "Login not successful send incorrect field login and password",
            retryAnalyzer = Retry.class)
    public void loginSendIncorrectPasswordAndLoginTest(){
        loginSteps
                .login(LOGIN_URL, "asdsad", "asdasd");
        Assert.assertEquals(loginPage.getLoginFormFailedMessage(),ERROR_TEXT_INCCORECT_FILD_LOGIN_FORM);
    }
}
