package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.TextBoxData;
import com.github.javafaker.Faker;

import static Utils.RandomUtils.getRandomEmail;
import static Utils.RandomUtils.getRandomString;

public class TextBoxTest extends TestBase {
    TextBoxPage textBox = new TextBoxPage();

    @Test
    @DisplayName("Успешное заполнение формы Text Box - проверка в блоке вывода")
    void fillTextBoxFormAndCheckOutputTest() {

        textBox.openPage()
                .setFullName(TextBoxData.fullName)
                .setEmail(TextBoxData.email)
                .setCurrentAddress(TextBoxData.currentAddress)
                .setPermanentAddress(TextBoxData.permanentAddress)
                .submit()
                .checkNameResult(TextBoxData.fullName)
                .checkEmailResult(TextBoxData.email)
                .checkCurrentAddressResult(TextBoxData.currentAddress)
                .checkPermanentAddressResult(TextBoxData.permanentAddress);
    }

    @Test
    @DisplayName("Успешное заполнение формы Text Box - проверка в блоке вывода")
    void successfullFillFormTest_withFaker() {
        Faker faker = new Faker();
        String userName = faker.name().fullName();
        String userEmail = faker.internet().emailAddress();
        String currentAddress = faker.address().fullAddress();
        String permanentAddress = faker.address().streetAddress() + ", " + faker.address().zipCode();

        textBox.openPage()
                .setFullName(userName)
                .setEmail(userEmail)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit()
                .checkNameResult(userName)
                .checkEmailResult(userEmail)
                .checkCurrentAddressResult(currentAddress)
                .checkPermanentAddressResult(permanentAddress);
    }

    @Test
    @DisplayName("Успешное заполнение формы Text Box - проверка в блоке вывода")
    void successfullFillFormTest_withUtils() {
        String userName = getRandomString(10, 10);
        String userEmail = getRandomEmail();
        String currentAddress = getRandomString(5, 50);
        String permanentAddress = getRandomString(5, 50);

        textBox.openPage()
                .setFullName(userName)
                .setEmail(userEmail)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit()
                .checkNameResult(userName)
                .checkEmailResult(userEmail)
                .checkCurrentAddressResult(currentAddress)
                .checkPermanentAddressResult(permanentAddress);
    }
}
