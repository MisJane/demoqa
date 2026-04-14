package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @Test
    @Tag("WEB")
    public void shouldFindClosedIssueWithTextTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".input-button").click();
        $("#query-builder-test").setValue("MisJane/demoqa")
                .sendKeys(Keys.ENTER);

        $(".search-title").click();

        $("#issues-tab").click();
        $$("div[class^='SectionFilterLink-module__title']")
                .shouldHave(size(2));

        $$("div[class^='SectionFilterLink-module__title']")
                .findBy(Condition.text("Closed"))
                .parent()
                .$("span")
                .shouldBe(Condition.visible, Condition.enabled)
                .click();

        $("body").shouldHave(Condition.text("Test Issue"));
    }
}
