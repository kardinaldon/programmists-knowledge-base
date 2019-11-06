package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {
    public String getAlphanumericRandomValue (int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
