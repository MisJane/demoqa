package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import static testdata.TestData.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PracticeFormTest extends TestBase {

    @Test
    @DisplayName("Успешное полное заполнение формы - всплыв. окно 'Thanks for submitting the form'")
    void successfulFillFormFullDataTest() {
        PracticeFormPage form = new PracticeFormPage();

        File picture = new File("src/test/resources/Picture.png");
        String pictureName = picture.getName();

        form.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .chooseGender(gender)
                .setMobile(mobile)
                .setBirthDate(day, month, year)
                .addSubject(subject1)
                .addSubject(subject2)
                .chooseHobby(hobby1)
                .chooseHobby(hobby2)
                .uploadPicture(picture)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());

        form.checkResult("Student Name", firstName + " " + lastName);
        form.checkResult("Student Email", email);
        form.checkResult("Gender", gender);
        form.checkResult("Mobile", mobile);
        form.checkResult("Date of Birth", day + " " + month + "," + year);
        form.checkResult("Subjects", subject1 + ", " + subject2);
        form.checkResult("Hobbies", hobby1 + ", " + hobby2);
        form.checkResult("Picture", pictureName);
        form.checkResult("Address", address);
        form.checkResult("State and City", state + " " + city);

    }

    @Test
    @DisplayName("Минимальное количество данных (только обязательные поля)")
    void submitWithMinimalRequiredDataTest() {
        PracticeFormPage form = new PracticeFormPage();

        form.openPage()
                .setFirstName(minFirstName)
                .setLastName(minLastName)
                .chooseGender(minGender)
                .setMobile(minMobile)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());

        form.checkResult("Student Name", minFirstName + " " + minLastName);
        form.checkResult("Gender", minGender);
        form.checkResult("Mobile", minMobile);

    }

    @Test
    @DisplayName("Негативный кейс. Пустая форма - нет всплыв. окна")
    void submitEmptyFormShouldNotOpenModalTest() {
        PracticeFormPage form = new PracticeFormPage();

        form.openPage()
                .submit();

        assertFalse(form.isModalOpened());

        form.checkNegativeValidation();
    }
}