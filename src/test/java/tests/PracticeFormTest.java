package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static testdata.TestData.*;

public class PracticeFormTest extends TestBase {

    @Test
    @DisplayName("Успешное полное заполнение формы - всплыв. окно 'Thanks for submitting the form'")
    void successfulFillFormTest() {
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

        assertEquals("Thanks for submitting the form",
                form.getModalTitleText());

        assertEquals(
                firstName + " " + lastName,
                form.getResultValue("Student Name")
        );
        assertEquals(email, form.getResultValue("Student Email"));
        assertEquals(gender, form.getResultValue("Gender"));
        assertEquals(mobile, form.getResultValue("Mobile"));
        assertEquals(
                day + " " + month + "," + year,
                form.getResultValue("Date of Birth")
        );
        assertEquals(
                subject1 + ", " + subject2,
                form.getResultValue("Subjects")
        );
        assertEquals(
                hobby1 + ", " + hobby2,
                form.getResultValue("Hobbies")
        );
        assertEquals(pictureName, form.getResultValue("Picture"));
        assertEquals(address, form.getResultValue("Address"));
        assertEquals(
                state + " " + city,
                form.getResultValue("State and City")
        );

    }
}
