package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;

public class RadioButtonPage {

    private final SelenideElement yesRadioLabel = $("label[for='yesRadio']");
    private final SelenideElement impressiveRadioLabel = $("label[for='impressiveRadio']");
    private final SelenideElement noRadioInput = $("#noRadio");
    private final SelenideElement resultText = $(".text-success");

    public RadioButtonPage openPage() {
        open("/");

        $$(".card-body").findBy(text("Elements")).click();
        $$(".router-link").findBy(text("Radio Button")).click();

        $(".main-header").shouldHave(text("Radio Button"));
        return this;
    }

    public RadioButtonPage selectYes() {
        yesRadioLabel.click();
        resultText.shouldBe(visible).shouldHave(text("Yes"));
        return this;
    }

    public RadioButtonPage selectImpressive() {
        impressiveRadioLabel.click();
        resultText.shouldBe(visible).shouldHave(text("Impressive"));
        return this;
    }

    public RadioButtonPage checkNoDisabled() {
        noRadioInput.shouldBe(disabled);
        return this;
    }

    public RadioButtonPage checkResultIs(String expected) {
        resultText.shouldBe(visible).shouldHave(text(expected));
        return this;
    }

    public RadioButtonPage checkNoResultVisible() {
        resultText.shouldNotBe(visible);
        return this;
    }

    public RadioButtonPage checkYesEnabled() {
        $("#yesRadio").shouldBe(enabled);
        return this;
    }

    public RadioButtonPage checkImpressiveEnabled() {
        $("#impressiveRadio").shouldBe(enabled);
        return this;
    }
}
