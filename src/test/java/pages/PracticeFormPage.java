package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;
import pages.components.StateCityComponent;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class PracticeFormPage {

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultsTableComponent resultsTableComponent = new ResultsTableComponent();
    private final StateCityComponent stateCityComponent = new StateCityComponent();

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddressTextArea = $("#currentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");

    public PracticeFormPage openPage() {
        open("/");

        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        $(".practice-form-wrapper").shouldBe(visible);
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public PracticeFormPage chooseGender(String genderText) {
        genderWrapper.$(byText(genderText)).click();
        return this;
    }

    public PracticeFormPage setMobile(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public PracticeFormPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage addSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public PracticeFormPage chooseHobby(String hobbyText) {
        hobbiesWrapper.$(byText(hobbyText)).click();
        return this;
    }

    public PracticeFormPage uploadPicture(File file) {
        uploadPictureInput.uploadFile(file);
        return this;
    }

    public PracticeFormPage setAddress(String value) {
        currentAddressTextArea.setValue(value);
        return this;
    }

    public PracticeFormPage selectState(String state) {
        stateCityComponent.selectState(state);
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        stateCityComponent.selectCity(city);
        return this;
    }

    public PracticeFormPage submit() {
        submitButton.scrollTo().click();
        return this;
    }

    public String getModalTitleText() {
        return modalTitle.getText();
    }

    public String getResultValue(String key) {
        return resultsTableComponent.getValue(key);
    }

    public PracticeFormPage checkResult(String key, String value) {
        resultsTableComponent.checkResult(key, value);
        return this;
    }

    public PracticeFormPage checkNegativeValidation() {
        resultsTableComponent.negativeCheck();
        return this;
    }

    public boolean isModalOpened() {
        return modalTitle.is(visible);
    }
}
