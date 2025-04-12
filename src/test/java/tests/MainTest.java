package tests;

import core.Retry;
import org.testng.annotations.Test;

public class MainTest extends BaseTest{

    @Test(description = "All Flow app",
            retryAnalyzer = Retry.class)
    public void checkAllStepTest(){
        registrationSteps
                .registration(REGISTRATION_URL, LOGIN, PASSWORD, PASSWORD);
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .createPost("test");
        diarySteps
                .deletePost();
    }
}
