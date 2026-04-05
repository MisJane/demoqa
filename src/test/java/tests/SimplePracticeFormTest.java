package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testdata.TestData;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimplePracticeFormTest extends TestBase {

    private String getResultValue(String label) {
        return $(".table-responsive").$(byText(label))
                .parent()
                .$$("td").get(1)
                .getText();
    }

    @Test
    @DisplayName("Успешное заполнение формы 'Student Registration Form'- всплывающее окно 'Thanks for submitting the form'")
    void successfullFillDataSimpleFormTest() {
        TestData data = new TestData();
        String pictureName = "Picture.png";

        open("");

        executeJavaScript(
                "document.getElementById('fixedban')?.remove();" +
                        "document.querySelector('footer')?.remove();"
        );

        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        $(".practice-form-wrapper").shouldBe(visible);
        $("#firstName").shouldBe(visible).setValue(data.firstNameSimple);
        $("#lastName").setValue(data.lastNameSimple);
        $("#userEmail").setValue(data.emailSimple);
        $("#genterWrapper").$(byText(data.genderSimple)).click();
        $("#userNumber").setValue(data.mobileSimple);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(data.monthSimple);
        $(".react-datepicker__year-select").selectOption(data.yearSimple);
        $(".react-datepicker__day--0" + data.daySimple +
                ":not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue(data.subject1Simple).pressEnter();
        $("#subjectsInput").setValue(data.subject2Simple).pressEnter();

        $("#hobbiesWrapper").$(byText(data.hobby1Simple)).click();
        $("#hobbiesWrapper").$(byText(data.hobby2Simple)).click();

        $("#uploadPicture").uploadFromClasspath(pictureName);

        $("#currentAddress").setValue(data.addressSimple);

        SelenideElement stateDropdown = $("#state");
        stateDropdown.scrollTo();
        stateDropdown.click();
        $("#stateCity-wrapper").$(byText(data.stateSimple)).click();

        SelenideElement cityDropdown = $("#city");
        cityDropdown.scrollTo();
        cityDropdown.click();
        $("#stateCity-wrapper").$(byText(data.citySimple)).click();

        $("#submit").scrollTo().click();


        assertEquals("Thanks for submitting the form",
                $("#example-modal-sizes-title-lg").getText());

        assertEquals(
                data.firstNameSimple + " " + data.lastNameSimple,
                getResultValue("Student Name")
        );
        assertEquals(data.emailSimple, getResultValue("Student Email"));
        assertEquals(data.genderSimple, getResultValue("Gender"));
        assertEquals(data.mobileSimple, getResultValue("Mobile"));
        assertEquals(
                data.daySimple + " " + data.monthSimple + "," + data.yearSimple,
                getResultValue("Date of Birth")
        );
        assertEquals(
                data.subject1Simple + ", " + data.subject2Simple,
                getResultValue("Subjects")
        );
        assertEquals(
                data.hobby1Simple + ", " + data.hobby2Simple,
                getResultValue("Hobbies")
        );
        assertEquals(pictureName, getResultValue("Picture"));
        assertEquals(data.addressSimple, getResultValue("Address"));
        assertEquals(
                data.stateSimple + " " + data.citySimple,
                getResultValue("State and City")
        );
    }
}
