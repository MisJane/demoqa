package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    public String getValue(String key) {
        return $(".table-responsive")
                .$(byText(key))
                .parent()
                .$$("td").get(1)
                .getText();
    }

    public void checkResult(String key, String value) {
        $(".table-responsive")
                .$(byText(key))
                .parent()
                .$$("td").get(1)
                .shouldHave(text(value));
    }

    public void negativeCheck() {
        $("form#userForm").shouldHave(
                com.codeborne.selenide.Condition.attribute("class", "was-validated")
        );
    }
}
