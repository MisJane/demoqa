package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class StepTest {
    private static final String REPOSITORY = "MisJane/demoqa";
    private static final String ISSUEName = "Test Issue";

    @Test
    @Tag("WEB")
    public void shouldFindClosedIssueWithTextLambdaTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });

        step("Ищем репу " + REPOSITORY, () -> {
            $(".input-button").click();
            $("#query-builder-test").setValue(REPOSITORY)
                    .sendKeys(Keys.ENTER);

            $(".search-title").click();
        });

        step("Переход в таб issue " + REPOSITORY, () -> {
            $("#issues-tab").click();
        });

        step("Идем в закрытые issue " + REPOSITORY, () -> {
            $$("div[class^='SectionFilterLink-module__title']")
                    .shouldHave(size(2));

            $$("div[class^='SectionFilterLink-module__title']")
                    .findBy(Condition.text("Closed"))
                    .parent()
                    .$("span")
                    .shouldBe(Condition.visible, Condition.enabled)
                    .click();
        });

        step("Проверяем наличие закрытой issue с наименованием " + ISSUEName, () -> {
            $("body").shouldHave(Condition.text(ISSUEName));
        });
    }

    @Test
    @Tag("WEB")
    public void shouldFindClosedIssueWithTextAnnotationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldFindClosedIssueWithText(ISSUEName);


    }
}
