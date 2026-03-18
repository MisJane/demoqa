package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    public void checkResult(String key, String value) {
        $(".table-responsive")
                .$(byText(key))
                .parent()
                .shouldHave(text(value));
    }

    public void negativeCheck() {
        $("form#userForm").shouldHave(
                com.codeborne.selenide.Condition.attribute("class", "was-validated")
        );
    }
}
