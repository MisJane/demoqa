package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static testdata.TestData.*;

public class SimplePracticeFormTest extends TestBase {

    private String getResultValue(String label) {
        return $(".table-responsive").$(byText(label))
                .parent()
                .$$("td").get(1)
                .getText();
    }

    @Test
    @DisplayName("Успешное заполнение формы 'Student Registration Form' - всплывающее окно 'Thanks for submitting the form'")
    void fillSimpleForm() {
        File picture = new File("src/test/resources/Picture.png");
        String pictureName = picture.getName();

        open("/");

        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        $(".practice-form-wrapper").shouldBe(visible);
        $("#firstName").shouldBe(visible).setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();

        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();

        $("#uploadPicture").uploadFile(picture);

        $("#currentAddress").setValue(address);

        SelenideElement stateDropdown = $("#state");
        stateDropdown.scrollTo();
        stateDropdown.click();
        $("#stateCity-wrapper").$(byText(state)).click();

        SelenideElement cityDropdown = $("#city");
        cityDropdown.scrollTo();
        cityDropdown.click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").scrollTo().click();


        assertEquals("Thanks for submitting the form",
                $("#example-modal-sizes-title-lg").getText());

        assertEquals(
                firstName + " " + lastName,
                getResultValue("Student Name")
        );
        assertEquals(email, getResultValue("Student Email"));
        assertEquals(gender, getResultValue("Gender"));
        assertEquals(mobile, getResultValue("Mobile"));
        assertEquals(
                day + " " + month + "," + year,
                getResultValue("Date of Birth")
        );
        assertEquals(
                subject1 + ", " + subject2,
                getResultValue("Subjects")
        );
        assertEquals(
                hobby1 + ", " + hobby2,
                getResultValue("Hobbies")
        );
        assertEquals(pictureName, getResultValue("Picture"));
        assertEquals(address, getResultValue("Address"));
        assertEquals(
                state + " " + city,
                getResultValue("State and City")
        );
    }
}
