package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.TextBoxData;

public class TextBoxTest extends TestBase {

    @Test
    @DisplayName("Успешное заполнение формы Text Box - проверка в блоке вывода")
    void fillTextBoxFormAndCheckOutput() {
        TextBoxPage textBox = new TextBoxPage();

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
}
