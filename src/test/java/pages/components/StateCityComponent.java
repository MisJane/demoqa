package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateCityComponent {

    public void selectState(String state) {
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
    }

    public void selectCity(String city) {
        $("#city").scrollTo().click();
        $("#stateCity-wrapper").$(byText(city)).click();
    }
}
