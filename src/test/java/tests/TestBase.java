package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

/*    @AfterEach
    void afterEach() {
        TestInfo testInfo = null;
        if (testInfo.getTestMethod().isPresent()) {
            screenshot(testInfo.getDisplayName());
        }
    }*/
}
