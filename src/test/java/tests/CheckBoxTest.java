package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CheckBoxPage;

public class CheckBoxTest extends TestBase {

    @Test
    @DisplayName("Все вложенные чекбоксы отмечены и отображаются в результатах")
    void selectHomeShowsAllInResult() {
        CheckBoxPage page = new CheckBoxPage();

        page.openPage()
                .expandAll()
                .toggleNode("Home")
                .checkResultContains(
                        "home",
                        "desktop",
                        "notes",
                        "commands",
                        "documents",
                        "downloads"
                );
    }

    @Test
    @DisplayName("Выбор Desktop - в результатах только ветка desktop")
    void selectDesktopOnly() {
        CheckBoxPage page = new CheckBoxPage();

        page.openPage()
                .expandAll()
                .toggleNode("Desktop")
                .checkResultContains("desktop", "notes", "commands");
    }
}
