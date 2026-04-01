package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import testdata.TestData;
import com.github.javafaker.Faker;

import static testdata.TestData.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PracticeFormTest extends TestBase {
    PracticeFormPage form = new PracticeFormPage();

    @Test
    @DisplayName("Успешное полное заполнение формы - всплыв. окно 'Thanks for submitting the form'")
    void successfulFillFormFullDataTest() {

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
                .uploadPicture(pictureName)
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
    @DisplayName("Успешное полное заполнение формы - всплыв. окно 'Thanks for submitting the form'")
    void successfulFillFormFullDataTest_WithFaker() {
        Faker faker = new Faker();
        TestData.initStateCities();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().safeEmailAddress();
        String gender = faker.random().nextBoolean() ? "Male" : "Female";
        // String mobile = faker.phoneNumber().phoneNumber().substring(0, 10);   //оставила пока для примера
        String mobile = faker.number().digits(10);
        String day = String.format("%02d", faker.number().numberBetween(1, 28));
        String month = TestData.getRandomMonth();
        String year = String.valueOf(faker.number().numberBetween(1980, 2005));
        String subject1 = TestData.getRandomSubject();
        String subject2 = TestData.getRandomSubject();
        String hobby1 = TestData.getRandomHobby();
        String hobby2 = TestData.getRandomHobby();
        String address = faker.address().streetAddress();
        String[] stateCity = TestData.getRandomStateCityPair();
        String state = stateCity[0];
        String city = stateCity[1];

        File picture = new File("src/test/resources/Picture.png");
        String pictureName = picture.getName();

        form.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .chooseGender(gender)
                .setMobile(mobile)
                .setBirthDate(day, month, year)
                .addSubject(getRandomSubject())
                .addSubject(getRandomSubject())
                .chooseHobby(getRandomHobby())
                .chooseHobby(getRandomHobby())
                .uploadPicture(pictureName)
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