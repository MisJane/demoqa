package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import com.github.javafaker.Faker;

import static testdata.TestData.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        initStateCities();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().safeEmailAddress();
        String gender = faker.random().nextBoolean() ? "Male" : "Female";
        // String mobile = faker.phoneNumber().phoneNumber().substring(0, 10);
        String mobile = faker.number().digits(10);
        String day = String.format("%02d", faker.number().numberBetween(1, 28));
        String month = getRandomMonth();
        String year = String.valueOf(faker.number().numberBetween(1980, 2005));
        String subject1 = getRandomSubject();
        String subject2 = getRandomSubject();
        String hobby1 = getRandomHobby();
        String hobby2 = getRandomHobby();
        String address = faker.address().streetAddress();
        String[] stateCity = getRandomStateCityPair();
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

    private String getRandomMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return months[new Faker().random().nextInt(months.length)];
    }

    private String getRandomSubject() {
        String[] subjects = {"Maths", "Physics", "Chemistry", "Biology", "Computer Science",
                "Economics", "History", "Civics"};
        return subjects[new Faker().random().nextInt(subjects.length)];
    }

    private String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return hobbies[new Faker().random().nextInt(hobbies.length)];
    }

    private final Map<String, String[]> stateToCities = new HashMap<>();

    private void initStateCities() {
        stateToCities.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        stateToCities.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        stateToCities.put("Haryana", new String[]{"Karnal", "Panipat"});
        stateToCities.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }

    private String[] getRandomStateCityPair() {
        String[][] pairs = {
                {"NCR", "Delhi"}, {"NCR", "Gurgaon"}, {"NCR", "Noida"},
                {"Uttar Pradesh", "Agra"}, {"Uttar Pradesh", "Lucknow"}, {"Uttar Pradesh", "Merrut"},
                {"Haryana", "Karnal"}, {"Haryana", "Panipat"},
                {"Rajasthan", "Jaipur"}, {"Rajasthan", "Jaiselmer"}
        };
        return pairs[new Faker().random().nextInt(pairs.length)];
    }

}