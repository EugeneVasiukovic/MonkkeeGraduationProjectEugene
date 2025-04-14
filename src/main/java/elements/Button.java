package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
@NoArgsConstructor
public class Button {
    String label;
    private static final String  POST_CONTENT = "//a[@class='entries__entry']//div[contains(text(), '%s')]";
    private static final String SELECT_DATE = "//td[@class='day' and text()='%s']";

    public void click(SelenideElement selenideElement) {
        selenideElement.shouldBe(Condition.visible).click();
    }

    public void clickContent() {
        $x(String.format(POST_CONTENT, label)).shouldBe(Condition.visible).click();
    }

    public void clickDate() {
        $x(String.format(SELECT_DATE, label)).shouldBe(Condition.visible).click();
    }
}
