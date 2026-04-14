package allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    @Test
    @Tag("WEB")
    @Feature("Issue в репе")
    @Story("Создание issue")
    @Owner("MisJane")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "GitHubTesting", url = "https://testing.github.com")
    @DisplayName("Создание issue для авторизованного пользака")
    public void staticLabelsTest() {
    }

    @Test
    @Tag("WEB")
    public void dynamicLabelsTest() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание issue для авторизованного пользака")
        );
        if (true) {
            Allure.feature("Issue в репе");
        } else {
            Allure.feature("Issue не в репе");
        }
        Allure.story("Создание issue");
        Allure.label("owner", "MisJane");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("Testing", "https://testing.github.com");
    }
}
