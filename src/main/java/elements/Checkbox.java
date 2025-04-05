package elements;

import static com.codeborne.selenide.Selenide.$x;

public class Checkbox {
    String label;
    private static final String CHECKBOX_LOCATOR = "//*[@id='%s']";

    public Checkbox(String label) {
        this.label = label;
    }

    public void checkboxClick(){
        $x(String.format(CHECKBOX_LOCATOR, label)).click();
    }


}
