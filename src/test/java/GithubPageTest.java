import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class GithubPageTest {

    @Test
    void checkEnterprisePage() {
        Configuration.browserSize = "1920x1080";

        open("https://github.com");

        $$(".HeaderMenu-link")
                .findBy(text("Solutions"))
                .shouldBe(visible)
                .hover();

        $$(".HeaderMenu-dropdown-link")
                .findBy(text("Enterprise"))
                .shouldBe(visible)
                .click();

        $("#hero-section-brand-heading")
                .shouldBe(visible)
                .shouldHave(text("The AI-powered developer platform"));
    }

}
