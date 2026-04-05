package testdata;

import com.github.javafaker.Faker;

public class TestData {

    private final Faker faker = new Faker();

    public final String firstNameSimple = "Valar";
    public final String lastNameSimple = "Morghules";
    public final String emailSimple = "em@il.it";
    public final String genderSimple = "Male";
    public final String mobileSimple = "1234567890";
    public final String daySimple = "01";
    public final String monthSimple = "April";
    public final String yearSimple = "2000";
    public final String subject1Simple = "Maths";
    public final String subject2Simple = "Physics";
    public final String hobby1Simple = "Sports";
    public final String hobby2Simple = "Music";
    public final String pictureNameSimple = "Picture.png";
    public final String addressSimple = "Some current address, 1-2, 3";
    public final String stateSimple = "NCR";
    public final String citySimple = "Delhi";

    public final String minFirstName = "A";
    public final String minLastName = "B";
    public final String minGender = "Male";
    public final String minMobile = "9999999999";

    public String getRandomMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    public String getRandomSubject() {
        String[] subjects = {"Maths", "Physics", "Chemistry", "Biology", "Computer Science",
                "Economics", "History", "Civics"};
        return subjects[faker.random().nextInt(subjects.length)];
    }

    public String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return hobbies[faker.random().nextInt(hobbies.length)];
    }

   //допфикс для явного разделения
    private String[] twoDistinctHobbies() {
        String h1 = getRandomHobby();
        String h2;
        do {
            h2 = getRandomHobby();
        } while (h2.equals(h1));
        return new String[]{h1, h2};
    }

    private String[] twoDistinctSubjects() {
        String s1 = getRandomSubject();
        String s2;
        do {
            s2 = getRandomSubject();
        } while (s2.equals(s1));
        return new String[]{s1, s2};
    }

    public String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String selectCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }

    public RandomPracticeFormData randomPracticeFormData() {
        String state = getRandomState();
        String[] subjects = twoDistinctSubjects();
        String[] hobbies = twoDistinctHobbies();
        return new RandomPracticeFormData(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().safeEmailAddress(),
                faker.random().nextBoolean() ? "Male" : "Female",
                faker.phoneNumber().subscriberNumber(10),
                String.format("%02d", faker.number().numberBetween(1, 28)),
                getRandomMonth(),
                String.valueOf(faker.number().numberBetween(1980, 2005)),
                subjects[0],
                subjects[1],
                hobbies[0],
                hobbies[1],
                faker.address().streetAddress(),
                state,
                selectCity(state)
        );
    }

    public final class RandomPracticeFormData {
        public final String firstName;
        public final String lastName;
        public final String email;
        public final String gender;
        public final String mobile;
        public final String day;
        public final String month;
        public final String year;
        public final String subject1;
        public final String subject2;
        public final String hobby1;
        public final String hobby2;
        public final String address;
        public final String state;
        public final String city;

        private RandomPracticeFormData(String firstName,
                                       String lastName,
                                       String email,
                                       String gender,
                                       String mobile,
                                       String day,
                                       String month,
                                       String year,
                                       String subject1,
                                       String subject2,
                                       String hobby1,
                                       String hobby2,
                                       String address,
                                       String state,
                                       String city) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.gender = gender;
            this.mobile = mobile;
            this.day = day;
            this.month = month;
            this.year = year;
            this.subject1 = subject1;
            this.subject2 = subject2;
            this.hobby1 = hobby1;
            this.hobby2 = hobby2;
            this.address = address;
            this.state = state;
            this.city = city;
        }
    }
}
