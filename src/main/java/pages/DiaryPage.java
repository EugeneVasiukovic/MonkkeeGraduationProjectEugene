package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class DiaryPage extends BasePage {
    private static final SelenideElement BUTTON_CREATE_POST = $(By.id("create-entry"));
    private static final SelenideElement BUTTON_PRINT = $x("//a[contains(@title, 'Print')]");
    private static final SelenideElement BUTTON_HOME = $(By.id("back-to-overview"));
    private static final SelenideElement BUTTON_SAVE_NEW_POST = $x("//a[@title='Save']");
    private static final SelenideElement ENTRY_AREA = $(By.id("editable"));
    private static final String POST_CONTENT = "//a[@class='entries__entry']//div[contains(text(), '%s')]";
    private static final SelenideElement BUTTON_CREATE_NEW_TAG = $(By.id("assign-new-tag"));
    private static final SelenideElement POST_CHANGE_DATE_BUTTON = $(By.className("link-text-long"));
    private static final SelenideElement BUTTON_DELETE_POST = $x("//*[@id='delete-entries']");
    private static final SelenideElement SELECT_ALL_POST = $x("//div[@class=\"toolbar\"]//input[@type=\"checkbox\"]");
    private static final SelenideElement ENTRY_TOOLBAR = $x("//*[@class='cke_top']");
    private static final SelenideElement SELECT_DATE_BUTTON = $(By.id("date"));
    private static final SelenideElement ELEMENT_HOVER_TAG = $x("//a[@class='tag ng-binding']");
    private static final SelenideElement NEW_WINDOW_TEXT = $x("/html/body/p");
    private static final SelenideElement OLDER_BUTTON = $x("//a[@title='Older']");
    private static final SelenideElement BUTTON_TAG = $x("//*[@class='tag ng-binding']");

    private DiaryPage fillDiaryForm(String text) {
        log.info("Filling diary form with text: " + text);
        new Button().click(BUTTON_CREATE_POST);
        ENTRY_TOOLBAR.shouldBe(Condition.visible);
        ENTRY_AREA.setValue(text);
        return this;
    }

    /**
     * Creates a new post.
     * @param text Text of the post.
     * @return DiaryPage instance.
     */
    public DiaryPage createPost(String text) {
        log.info("Creating post with text: " + text);
        fillDiaryForm(text);
        new Button().click(BUTTON_SAVE_NEW_POST);
        new Button().click(BUTTON_HOME);
        log.info("Verifying post creation.");
        $x(String.format(POST_CONTENT, text)).shouldHave(Condition.text(text));
        return this;
    }

    /**
     * Creates a new post and adds a new tag.
     * @param text Text of the post.
     * @param nameTag Name of the tag.
     * @return DiaryPage instance.
     */
    public DiaryPage createPostAndAddNewTag(String text, String nameTag) {
        log.info("Creating post with text: " + text + " and adding new tag: " + nameTag);
        fillDiaryForm(text);
        new Input("new-tag").write(nameTag);
        new Button().click(BUTTON_CREATE_NEW_TAG);
        new Button().click(BUTTON_SAVE_NEW_POST);
        new Button().click(BUTTON_HOME);
        new Button().click(BUTTON_TAG);
        log.info("Verifying post creation with tag.");
        $x(String.format(POST_CONTENT, text)).shouldHave(Condition.text(text));
        return this;
    }

    /**
     * Deletes a tag in a created post.
     * @param textPost Text of the post.
     * @return DiaryPage instance.
     */
    public DiaryPage deleteAnTagInACreatedPost(String textPost) {
        log.info("Deleting tag in post with text: " + textPost);
        new Button(textPost).clickContent();
        ELEMENT_HOVER_TAG.hover().click();
        log.info("Verifying tag deletion.");
        ELEMENT_HOVER_TAG.shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Updates a post by adding new text.
     * @param oldTextPost Old text of the post.
     * @param newText New text to be added.
     * @return DiaryPage instance.
     */
    public DiaryPage updatePostAddNewText(String oldTextPost, String newText) {
        log.info("Updating post with old text: " + oldTextPost + " to new text: " + newText);
        new Button(oldTextPost).clickContent();
        ENTRY_AREA.clear();
        ENTRY_AREA.setValue(newText);
        new Button().click(BUTTON_SAVE_NEW_POST);
        new Button().click(BUTTON_HOME);
        log.info("Verifying post update.");
        $x(String.format(POST_CONTENT, newText)).shouldHave(Condition.text(newText));
        return this;
    }

    /**
     * Creates a post on a future date.
     * @param text Text of the post.
     * @param date Future date for the post.
     * @return DiaryPage instance.
     */
    public DiaryPage createPostOnFutureDate(String text, String date) {
        log.info("Creating post with text: " + text + " on future date: " + date);
        fillDiaryForm(text);
        new Button().click(POST_CHANGE_DATE_BUTTON);
        new Button().click(SELECT_DATE_BUTTON);
        new Button(date).clickDate();
        new Button().click(BUTTON_SAVE_NEW_POST);
        new Button().click(BUTTON_HOME);
        log.info("Verifying post creation on future date.");
        $x(String.format(POST_CONTENT, text)).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Goes to a post and clicks the print button.
     * @param textPost Text of the post.
     * @return Text from the new window.
     */
    public String goToPostAndClickPrint(String textPost) {
        log.info("Going to post with text: " + textPost + " and clicking print.");
        new Button(textPost).clickContent();
        new Button().click(BUTTON_PRINT);
        switchTo().window(1);
        String newWindowText = NEW_WINDOW_TEXT.getText();
        log.info("Text from new window: " + newWindowText);
        return newWindowText;
    }

    /**
     * Goes back to the last post.
     * @param textPost Text of the post.
     * @return DiaryPage instance.
     */
    public DiaryPage goBackToTheLastPost(String textPost) {
        log.info("Going back to the last post with text: " + textPost);
        new Button(textPost).clickContent();
        new Button().click(OLDER_BUTTON);
        log.info("Verifying navigation to the last post.");
        ENTRY_AREA.shouldHave(Condition.text(textPost));
        return this;
    }

    /**
     * Deletes a post.
     * @return DiaryPage instance.
     */
    public DiaryPage deletePost() {
        log.info("Deleting post.");
        new Button().click(SELECT_ALL_POST);
        BUTTON_DELETE_POST.shouldBe(Condition.enabled);
        new Button().click(BUTTON_DELETE_POST);
        switchTo().alert().accept();
        BUTTON_DELETE_POST.shouldNotBe(Condition.disabled);
        log.info("Verifying post deletion.");
        $x(String.format(POST_CONTENT, "")).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Verifies the text in the new window after clicking print.
     * @param expectedText Expected text in the new window.
     * @return True if the text matches, false otherwise.
     */
    public boolean verifyPrintWindowText(String expectedText) {
        log.info("Verifying text in the new window after clicking print.");
        return NEW_WINDOW_TEXT.shouldHave(Condition.text(expectedText)).exists();
    }
}