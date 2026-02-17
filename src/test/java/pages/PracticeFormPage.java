package pages;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

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
    private final SelenideElement stateDropdown = $("#state");
    private final SelenideElement cityDropdown = $("#city");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement resultTable = $(".table-responsive");
    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");

    public PracticeFormPage openPage() {
        open("/");

        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

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
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();
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
        stateDropdown.scrollTo();
        stateDropdown.click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        cityDropdown.scrollTo();
        cityDropdown.click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    public PracticeFormPage submit() {
        submitButton.scrollTo().click();
        return this;
    }

    public String getModalTitleText() {
        return modalTitle.getText();
    }

    public String getResultValue(String rowName) {
        return resultTable.$(byText(rowName))
                .parent()
                .$$("td").get(1)
                .getText();
    }
}
