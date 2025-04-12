package steps;

import pages.DiaryPage;
import io.qameta.allure.Step;

public class DiarySteps {
    DiaryPage diaryPage;

    public DiarySteps() {
        this.diaryPage = new DiaryPage();
    }

    @Step("Create a post with text: {text}")
    public void createPost(String text) {
        diaryPage
                .createPost(text);
    }

    @Step("Create a post with text: {text} and tag: {tag}")
    public void createPostWithTag(String text, String tag) {
        diaryPage
                .CreatePostAndAddNewTag(text, tag);
    }

    @Step("Delete tag from post with text: {textPost}")
    public void deleteTagFromPost(String textPost) {
        diaryPage
                .DeleteAnTagInACreatedPost(textPost);
    }

    @Step("Edit post text from {oldTextPost} to {newText}")
    public void editPostText(String oldTextPost, String newText) {
        diaryPage
                .updatePostAddNewText(oldTextPost, newText);
    }

    @Step("Create a post with text: {text} on future date: {date}")
    public void createPostOnFutureDate(String text, String date) {
        diaryPage
                .createPostOnFutureDate(text, date);
    }

    @Step("Print post with text: {textPost}")
    public void printPost(String textPost) {
        diaryPage.goToPostAndClickPrint(textPost);
        diaryPage.verifyPrintWindowText(textPost);
    }

    @Step("Go back to the last post with text: {textPost}")
    public void goBackToLastPost(String textPost) {
        diaryPage
                .goBackToTheLastPost(textPost);
    }

    @Step("Delete the created post")
    public void deletePost() {
        diaryPage
                .deletePost();
    }
}
