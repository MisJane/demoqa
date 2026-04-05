package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import testdata.TestData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PracticeFormTest extends TestBase {
    PracticeFormPage form = new PracticeFormPage();

    @Test
    @DisplayName("Успешное полное заполнение формы - всплыв. окно 'Thanks for submitting the form'")
    void successfulFillFormFullDataTest() {
        TestData data = new TestData();

        form.openPage()
                .setFirstName(data.firstNameSimple)
                .setLastName(data.lastNameSimple)
                .setEmail(data.emailSimple)
                .chooseGender(data.genderSimple)
                .setMobile(data.mobileSimple)
                .setBirthDate(data.daySimple, data.monthSimple, data.yearSimple)
                .addSubject(data.subject1Simple)
                .addSubject(data.subject2Simple)
                .chooseHobby(data.hobby1Simple)
                .chooseHobby(data.hobby2Simple)
                .uploadPicture(data.pictureNameSimple)
                .setAddress(data.addressSimple)
                .selectState(data.stateSimple)
                .selectCity(data.citySimple)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());

        form.checkResult("Student Name", data.firstNameSimple + " " + data.lastNameSimple);
        form.checkResult("Student Email", data.emailSimple);
        form.checkResult("Gender", data.genderSimple);
        form.checkResult("Mobile", data.mobileSimple);
        form.checkResult("Date of Birth", data.daySimple + " " + data.monthSimple + "," + data.yearSimple);
        form.checkResult("Subjects", data.subject1Simple + ", " + data.subject2Simple);
        form.checkResult("Hobbies", data.hobby1Simple + ", " + data.hobby2Simple);
        form.checkResult("Picture", data.pictureNameSimple);
        form.checkResult("Address", data.addressSimple);
        form.checkResult("State and City", data.stateSimple + " " + data.citySimple);

    }

    @Test
    @DisplayName("Успешное полное заполнение формы - всплыв. окно 'Thanks for submitting the form'")
    void successfulFillFormFullDataTest_WithFaker() {
        TestData data = new TestData();
        TestData.RandomPracticeFormData random = data.randomPracticeFormData();

        form.openPage()
                .setFirstName(random.firstName)
                .setLastName(random.lastName)
                .setEmail(random.email)
                .chooseGender(random.gender)
                .setMobile(random.mobile)
                .setBirthDate(random.day, random.month, random.year)
                .addSubject(random.subject1)
                .addSubject(random.subject2)
                .chooseHobby(random.hobby1)
                .chooseHobby(random.hobby2)
                .uploadPicture(data.pictureNameSimple)
                .setAddress(random.address)
                .selectState(random.state)
                .selectCity(random.city)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());

        form.checkResult("Student Name", random.firstName + " " + random.lastName);
        form.checkResult("Student Email", random.email);
        form.checkResult("Gender", random.gender);
        form.checkResult("Mobile", random.mobile);
        form.checkResult("Date of Birth", random.day + " " + random.month + "," + random.year);
        form.checkResult("Subjects", random.subject1 + ", " + random.subject2);
        form.checkResult("Hobbies", random.hobby1 + ", " + random.hobby2);
        form.checkResult("Picture", data.pictureNameSimple);
        form.checkResult("Address", random.address);
        form.checkResult("State and City", random.state + " " + random.city);
    }

    @Test
    @DisplayName("Минимальное количество данных (только обязательные поля)")
    void submitWithMinimalRequiredDataTest() {
        PracticeFormPage form = new PracticeFormPage();
        TestData data = new TestData();

        form.openPage()
                .setFirstName(data.minFirstName)
                .setLastName(data.minLastName)
                .chooseGender(data.minGender)
                .setMobile(data.minMobile)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());

        form.checkResult("Student Name", data.minFirstName + " " + data.minLastName);
        form.checkResult("Gender", data.minGender);
        form.checkResult("Mobile", data.minMobile);
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
