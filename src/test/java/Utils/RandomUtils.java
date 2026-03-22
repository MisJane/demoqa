package Utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {

    public static String getRandomString(int minLength, int maxLength) {

        String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();
        SecureRandom rand = new SecureRandom();
        int length = rand.nextInt(maxLength - minLength + 1) + minLength;

        for (int i = 0; i < length; i++) {
            result.append(LETTERS.charAt(rand.nextInt(LETTERS.length())));
        }
        return result.toString();
    }

    public static String getRandomEmail() {
        return format("%s@%s.com", getRandomString(3, 7), getRandomString(5, 8));
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
        /*SecureRandom rand = new SecureRandom();
        return rand.nextInt(max - min + 1) + min;*/
    }

    public static String getRandomPhoneNumber() {
        String phoneTemplate = "+%s (%s) %s - %s - %s";
        return format(phoneTemplate, getRandomInt(111, 999), getRandomInt(111, 999),
                getRandomInt(11, 99), getRandomInt(11, 99));
    }

    public static String getRandomGender() {
        String[] gender = {"Male", "Female", "Other"};
        return getRandomItemFromStringArray(gender);
    }

    public static String getRandomItemFromStringArray(String[] stringArray) {

        int arrayLength = stringArray.length;
        int randomIndex = getRandomInt(0, arrayLength - 1);

        return stringArray[randomIndex];
    }
}
