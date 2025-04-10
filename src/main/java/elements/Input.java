package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class Input {
    String label;
    private static final String INPUT_LOCATOR = "//*[@id='%s']";

    public Input(String label) {
        this.label = label;
    }

    public Input write(String text){
        $x(String.format(INPUT_LOCATOR, label)).shouldBe(Condition.visible).setValue(text);
        return this;
    }
}