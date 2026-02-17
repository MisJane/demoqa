package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RadioButtonPage;

public class RadioButtonTest extends TestBase {

    @Test
    @DisplayName("Выбор Yes - отображается 'You have selected Yes', No отключён")
    void selectYes() {
        RadioButtonPage page = new RadioButtonPage();

        page.openPage()
                .checkYesEnabled()
                .checkImpressiveEnabled()
                .checkNoDisabled()
                .selectYes()
                .checkResultIs("Yes");
    }

    @Test
    @DisplayName("Выбор Impressive - отображается 'You have selected Impressive', No отключён")
    void selectImpressive() {
        RadioButtonPage page = new RadioButtonPage();

        page.openPage()
                .checkYesEnabled()
                .checkImpressiveEnabled()
                .checkNoDisabled()
                .selectImpressive()
                .checkResultIs("Impressive");
    }
}
