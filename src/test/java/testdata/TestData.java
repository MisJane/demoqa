package testdata;

import com.github.javafaker.Faker;

public class TestData {
    static Faker faker = new Faker();

    public static String firstName = "Valar",
            lastName = "Morghules",
            email = "em@il.it",
            gender = "Male",
            mobile = "1234567890",
            day = "01",
            month = "April",
            year = "2000",
            subject1 = "Maths",
            subject2 = "Physics",
            hobby1 = "Sports",
            hobby2 = "Music",
            pictureName = "Picture.png",
            address = "Some current address, 1-2, 3",
            state = "NCR",
            city = "Delhi";

    public static String minFirstName = "A",
            minLastName = "B",
            minGender = "Male",
            minMobile = "9999999999";


    public static String getRandomMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    public static String getRandomSubject() {
        String[] subjects = {"Maths", "Physics", "Chemistry", "Biology", "Computer Science",
                "Economics", "History", "Civics"};
        return subjects[new Faker().random().nextInt(subjects.length)];
    }

    public static String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return hobbies[new Faker().random().nextInt(hobbies.length)];
    }

   /* public static final Map<String, String[]> stateToCities = new HashMap<>();

    public static void initStateCities() {
        stateToCities.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        stateToCities.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        stateToCities.put("Haryana", new String[]{"Karnal", "Panipat"});
        stateToCities.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }

    public static String[] getRandomStateCityPair() {
        String[][] pairs = {
                {"NCR", "Delhi"}, {"NCR", "Gurgaon"}, {"NCR", "Noida"},
                {"Uttar Pradesh", "Agra"}, {"Uttar Pradesh", "Lucknow"}, {"Uttar Pradesh", "Merrut"},
                {"Haryana", "Karnal"}, {"Haryana", "Panipat"},
                {"Rajasthan", "Jaipur"}, {"Rajasthan", "Jaiselmer"}
        };
        return pairs[new Faker().random().nextInt(pairs.length)];
    }*/

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String selectCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
}
