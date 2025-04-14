package steps;

import pages.DiaryPage;
import io.qameta.allure.Step;

public class DiarySteps {
    DiaryPage diaryPage;

    public DiarySteps() {
        this.diaryPage = new DiaryPage();
    }

    @Step("Create a post with text: {text}")
    public void createPostAndCheckCreated(String text) {
        diaryPage
                .createPost(text);
    }

    @Step("Create a post with text: {text} and tag: {tag}")
    public void createPostWithTagAndCheckCreated(String text, String tag) {
        diaryPage
                .createPostAndAddNewTag(text, tag);
    }

    @Step("Delete tag from post with text: {textPost}")
    public void deleteTagFromPostAndCheckDeleted(String textPost) {
        diaryPage
                .deleteAnTagInACreatedPost(textPost);
    }

    @Step("Edit post text from {oldTextPost} to {newText}")
    public void editPostTextAndCheckUpdated(String oldTextPost, String newText) {
        diaryPage
                .updatePostAddNewText(oldTextPost, newText);
    }

    @Step("Create a post with text: {text} on future date: {date}")
    public void createPostOnFutureDateAndCheckCreated(String text, String date) {
        diaryPage
                .createPostOnFutureDate(text, date);
    }

    @Step("Print post with text: {textPost}")
    public void printPostAndCheckPrinted(String textPost) {
        diaryPage
                .goToPostAndClickPrint(textPost);
        diaryPage
                .verifyPrintWindowText(textPost);
    }

    @Step("Go back to the last post with text: {textPost}")
    public void goBackToLastPostAndCheckNavigated(String textPost) {
        diaryPage
                .goBackToTheLastPost(textPost);
    }

    @Step("Delete the created post")
    public void deletePostAndCheckDeleted() {
        diaryPage
                .deletePost();
    }
}