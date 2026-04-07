package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.PracticeFormPage;
import testdata.TestData;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Tag("WEB")
public class PracticeFormTest extends TestBase {
    PracticeFormPage form = new PracticeFormPage();

    enum GenderOption {
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        final String uiValue;

        GenderOption(String uiValue) {
            this.uiValue = uiValue;
        }
    }

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

    @ParameterizedTest
    @DisplayName("Минимальное количество данных (только обязательные поля) - параметризованный")
    @CsvSource({
            "A,B,Male,9999999999",
            "C,D,Female,8888888888",
            "E,F,Other,7777777777"})
    void submitWithMinimalRequiredDataParameterizedTest(String firstName, String lastName, String gender, String mobile) {
        form.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setMobile(mobile)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());

        form.checkResult("Student Name", firstName + " " + lastName);
        form.checkResult("Gender", gender);
        form.checkResult("Mobile", mobile);
    }

    @ParameterizedTest
    @MethodSource("stateCityProvider")
    @DisplayName("Параметризованный (MethodSource): валидные пары state/city")
    void submitWithStateCityMethodSourceTest(String state, String city) {
        TestData data = new TestData();

        form.openPage()
                .setFirstName(data.minFirstName)
                .setLastName(data.minLastName)
                .chooseGender(data.minGender)
                .setMobile(data.minMobile)
                .selectState(state)
                .selectCity(city)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());
        form.checkResult("State and City", state + " " + city);
    }

    @ParameterizedTest
    @EnumSource(GenderOption.class)
    @DisplayName("Параметризованный (EnumSource): все значения Gender")
    void submitWithAllGenderValuesEnumSourceTest(GenderOption genderOption) {
        TestData data = new TestData();

        form.openPage()
                .setFirstName(data.minFirstName)
                .setLastName(data.minLastName)
                .chooseGender(genderOption.uiValue)
                .setMobile(data.minMobile)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());
        form.checkResult("Gender", genderOption.uiValue);
    }

    @ParameterizedTest(name = "[{index}] firstname={0} lastname={1} gender={2} mobile={3}")
    @CsvFileSource(resources = "/testdata/minimal-required-data.csv", numLinesToSkip = 1)
    @DisplayName("Параметризованный (CsvFileSource): обязательные поля")
    void submitWithMinimalRequiredDataCsvFileSourceTest(String firstName, String lastName, String gender, String mobile) {
        form.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setMobile(mobile)
                .submit();

        assertEquals("Thanks for submitting the form", form.getModalTitleText());
        form.checkResult("Student Name", firstName + " " + lastName);
        form.checkResult("Gender", gender);
        form.checkResult("Mobile", mobile);
    }

    static Stream<Arguments> stateCityProvider() {
        return Stream.of(
                Arguments.of("NCR", "Delhi"),
                Arguments.of("Uttar Pradesh", "Lucknow"),
                Arguments.of("Haryana", "Karnal"),
                Arguments.of("Rajasthan", "Jaipur")
        );
    }
}
