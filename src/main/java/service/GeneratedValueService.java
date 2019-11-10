package service;

import dao.GeneratedValueDAO;
import models.GeneratedValues;
import utils.RandomGenerator;

import java.util.concurrent.TimeUnit;


public class GeneratedValueService {
    GeneratedValueDAO generatedValueDao = new GeneratedValueDAO();
    GeneratedValues generatedValues;
    RandomGenerator randomGenerator;

    public GeneratedValues findGeneratedValueById(int id) {
        return generatedValueDao.findById(id);
    }

    public GeneratedValues findGeneratedValuesByValue(String value) throws InterruptedException {
        return generatedValueDao.findByValue(value);
    }

    public GeneratedValues createGeneratedValue(int lengthOfValue, boolean useLetters, boolean useNumbers) {
        generatedValues = new GeneratedValues();
        randomGenerator = new RandomGenerator();
        generatedValues.setGeneratedValue(randomGenerator.getAlphanumericRandomValue(lengthOfValue, useLetters, useNumbers));
        generatedValueDao.save(generatedValues);
        return generatedValues;
    }

    public void deleteGeneratedValue(GeneratedValues generatedValues) {
        generatedValueDao.delete(generatedValues);
    }

    public void deferredDeletion (int id, int time) {
        generatedValues = generatedValueDao.findById(id);
        try {
            TimeUnit.SECONDS.sleep(time);
            generatedValueDao.delete(generatedValues);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
