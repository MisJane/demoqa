package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CheckBoxPage {

    private final SelenideElement expandAllButton = $("button[title='Expand all']");
    private final SelenideElement collapseAllButton = $("button[title='Collapse all']");
    private final SelenideElement resultBlock = $("#result");

    public CheckBoxPage openPage() {
        open("/");

        $$(".card-body").findBy(text("Elements")).click();
        $$(".router-link").findBy(text("Check Box")).click();

        $(".main-header").shouldHave(text("Check Box"));
        return this;
    }

    public CheckBoxPage expandAll() {
        expandAllButton.click();
        return this;
    }

    public CheckBoxPage collapseAll() {
        collapseAllButton.click();
        return this;
    }

    public CheckBoxPage toggleNode(String nodeName) {
        SelenideElement node = $(".check-box-tree-wrapper").$(String.valueOf(Condition.text(nodeName)));
        node.closest(".rct-node").$(".rct-checkbox").click();
        return this;
    }

    public CheckBoxPage checkResultContains(String... expected) {
        resultBlock.shouldBe(visible);
        for (String item : expected) {
            resultBlock.shouldHave(text(item));
        }
        return this;
    }

    public CheckBoxPage checkResultNotVisible() {
        resultBlock.shouldNotBe(visible);
        return this;
    }
}
