package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class TextBoxPage {

    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputBlock = $("#output");

    public TextBoxPage openPage() {
        open("/");

        $$(".card-body").findBy(text("Elements")).click();
        $$(".router-link").findBy(text("Text Box")).click();

        return this;
    }

    public TextBoxPage setFullName(String value) {
        fullNameInput.shouldBe(visible).setValue(value);
        return this;
    }

    public TextBoxPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage submit() {
        submitButton.scrollTo().click();
        outputBlock.shouldBe(visible);
        return this;
    }

    // проверки вывода
    public TextBoxPage checkNameResult(String expectedFullName) {
        outputBlock.$("#name").shouldHave(text("Name:"), text(expectedFullName));
        return this;
    }

    public TextBoxPage checkEmailResult(String expectedEmail) {
        outputBlock.$("#email").shouldHave(text("Email:"), text(expectedEmail));
        return this;
    }

    public TextBoxPage checkCurrentAddressResult(String expectedAddress) {
        outputBlock.$("p#currentAddress")
                .shouldHave(text("Current Address :"), text(expectedAddress));
        return this;
    }

    public TextBoxPage checkPermanentAddressResult(String expectedAddress) {
        outputBlock.$("p#permanentAddress")
                .shouldHave(text("Permananet Address :"), text(expectedAddress));
        return this;
    }
}
