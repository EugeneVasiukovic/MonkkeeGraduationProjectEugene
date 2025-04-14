package tests;

import core.Retry;
import org.testng.annotations.Test;

public class DiaryTest extends BaseTest {

    @Test(description = "Create post",
            retryAnalyzer = Retry.class,
            priority = 1)
    public void createPostTest() {
        loginSteps.
                login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps.
                createPostAndCheckCreated("Test1");
    }

    @Test(description = "Create a post with text and tag",
            retryAnalyzer = Retry.class,
            priority = 2)
    public void createPostWithTagTest() {
        loginSteps.
                login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .createPostWithTagAndCheckCreated("test2", "test2");
    }

    @Test(description = "Create a post with text and tag, and delete tag with post",
            retryAnalyzer = Retry.class,
            priority = 3)
    public void deleteTagWithPostTest() {
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .createPostWithTagAndCheckCreated("test3", "123");
        diarySteps
                .deleteTagFromPostAndCheckDeleted("test3");
    }

    @Test(description = "Edit post text",
            retryAnalyzer = Retry.class,
            priority = 4)
    public void editPostTextTest() {
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .editPostTextAndCheckUpdated("Test1", "TestTestTest");
    }

    @Test(description = "Create post on future date",
            retryAnalyzer = Retry.class,
            priority = 5)
    public void createPostOnFutureDateTest() {
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .createPostOnFutureDateAndCheckCreated("Lorem impsun", "30");
    }

    @Test(description = "Print post with text",
            retryAnalyzer = Retry.class,
            priority = 6)
    public void checkPrintPostTest() {
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .createPostAndCheckCreated("lorem impsun1");
        diarySteps
                .printPostAndCheckPrinted("lorem impsun1");
    }

    @Test(description = "Go back to the last post with text",
            retryAnalyzer = Retry.class,
            priority = 7)
    public void goBackPosttest() {
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .createPostAndCheckCreated("text321");
        diarySteps
                .createPostAndCheckCreated("text321");
        diarySteps
                .goBackToLastPostAndCheckNavigated("text321");
    }

    @Test(description = "Delete Post",
            retryAnalyzer = Retry.class,
            priority = 8)
    public void deletePostTest() {
        loginSteps
                .login(LOGIN_URL, LOGIN, PASSWORD);
        diarySteps
                .deletePostAndCheckDeleted();
    }
}
