package elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Checkbox {
    String label;
    private static final String CHECKBOX_LOCATOR = "//*[@id='%s']";

    public Checkbox(String label) {
        this.label = label;
    }

    public void setCheckboxValue(boolean selected) {
        SelenideElement checkbox = $x(String.format(CHECKBOX_LOCATOR, label));
        if (selected && !checkbox.isSelected()) {
            checkbox.click();
        } else if (!selected && checkbox.isSelected()) {
            checkbox.click();
        }
    }
}