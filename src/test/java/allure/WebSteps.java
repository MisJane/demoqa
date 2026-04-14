package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com/");

    }

    @Step("Ищем репу {repo}")
    public void searchForRepository(String repo) {
        $(".input-button").click();
        $("#query-builder-test").setValue(repo)
                .sendKeys(Keys.ENTER);


    }

    @Step("Клик по репозиторию {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(".search-title").click();
    }

    @Step("Идем в таб закрытые issue {issue}")
    public void openIssuesTab() {
        $("#issues-tab").click();
        $$("div[class^='SectionFilterLink-module__title']")
                .shouldHave(size(2));

        $$("div[class^='SectionFilterLink-module__title']")
                .findBy(Condition.text("Closed"))
                .parent()
                .$("span")
                .shouldBe(Condition.visible, Condition.enabled)
                .click();
    }

    @Step("Проверяем наличие закрытой issue с наименованием {issue}")
    public void shouldFindClosedIssueWithText(String issueName) {
        $("body").shouldHave(Condition.text(issueName));
    }

    @Attachment(value = "Screenshot", type = "img/png", fileExtension = "png")
    public byte[] takeSreenShot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
