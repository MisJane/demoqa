import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPageTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTests() {
        open("/text-box");

        $("#userName").setValue("TestUser");
        $("#userEmail").setValue("test@ema.il");
        $("#currentAddress").setValue("some address");
        $("#permanentAddress").setValue("some permanent address");
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text("TestUser"));
        $("#output #email").shouldHave(text("test@ema.il"));
        $("#output #currentAddress").shouldHave(text("some address"));
        $("#permanentAddress", 1).shouldHave(text("some permanent address"));

    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        if (testInfo.getTestMethod().isPresent()) {
            screenshot(testInfo.getDisplayName());
        }
    }
}
