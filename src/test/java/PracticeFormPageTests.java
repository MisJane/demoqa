import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPageTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTests() {
        open("/automation-practice-form");

        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("em@il.it");
        $("#userNumber").setValue("1234567890");
        $("#currentAddress").setValue("123 Main St");

        $(byText("Male")).click();
        $("#gender-radio-1").shouldBe(selected);
        $(byText("Sports")).click();
        $("#hobbies-checkbox-1").shouldBe(checked);
        $(byText("Reading")).click();
        $("#hobbies-checkbox-2").shouldBe(checked);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("2010");
        $(".react-datepicker__day--001").click();
        $("#dateOfBirthInput").shouldHave(value("01 Apr 2010"));

        $("#subjectsInput").setValue("Math").pressEnter();
        $(".subjects-auto-complete__multi-value").shouldHave(text("Maths"));

        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#uploadPicture").uploadFromClasspath("test-image.jpg");
        $("#uploadPicture").shouldHave(value("test-image.jpg"));

        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("John Doe"),
                text("em@il.it"),
                text("Male"),
                text("1234567890"),
                text("123 Main St"),
                text("Sports, Reading"),
                text("01 April,2010"),
                text("Maths"),
                text("NCR Delhi"),
                text("test-image.jpg")
        );

    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        if (testInfo.getTestMethod().isPresent()) {
            screenshot(testInfo.getDisplayName());
        }
    }
}
