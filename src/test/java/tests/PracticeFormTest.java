package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import testdata.TestData;

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
                .setFirstName(TestData.firstName)
                .setLastName(TestData.lastName)
                .setEmail(TestData.email)
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
                TestData.firstName + " " + TestData.lastName,
                form.getResultValue("Student Name")
        );
        assertEquals(TestData.email, form.getResultValue("Student Email"));
        assertEquals(TestData.gender, form.getResultValue("Gender"));
        assertEquals(TestData.mobile, form.getResultValue("Mobile"));
        assertEquals(
                TestData.day + " " + TestData.month + "," + TestData.year,
                form.getResultValue("Date of Birth")
        );
        assertEquals(
                TestData.subject1 + ", " + TestData.subject2,
                form.getResultValue("Subjects")
        );
        assertEquals(
                TestData.hobby1 + ", " + TestData.hobby2,
                form.getResultValue("Hobbies")
        );
        assertEquals(pictureName, form.getResultValue("Picture"));
        assertEquals(TestData.address, form.getResultValue("Address"));
        assertEquals(
                TestData.state + " " + TestData.city,
                form.getResultValue("State and City")
        );

    }
}
