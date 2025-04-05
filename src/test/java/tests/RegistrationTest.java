package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test(description = "Verification of successful user registration with valid data",
            retryAnalyzer = Retry.class)
    public void registrationSuccessTest() {
        registrationStep
                .registration
                        (REGISTRATION_URL, "qwerasd@gmail.com", "Rutube-2", "Rutube-2");
        Assert.assertEquals(registrationPage.isRegistrationSuccessful(), SUCCESS_REGISTRATION_TEXT);
    }

    @Test(description = "Verification of registration form filling without clicking OK",
            retryAnalyzer = Retry.class)
    public void registrationFormFillingTest() {
        registrationStep
                .registrationFormFillingTest
                        (REGISTRATION_URL,"qwerasd", "йцуйцу", "йцуйцу");
        Assert.assertTrue(registrationPage.isOkButtonDisabled());
    }

    @Test(description = "Verification Error message",
            retryAnalyzer = Retry.class)
    public void registrationVerificationErrorMessageTest() {
        registrationStep
                .fillRegistrationPageWithoutCheckboxes
                        (REGISTRATION_URL,"qwerasd@gmail.com", "Rutube-2", "Rutube-2");
        Assert.assertEquals(registrationPage.getErrorMessage(), ERROR_REGISTRATION_TEXT);
    }
}