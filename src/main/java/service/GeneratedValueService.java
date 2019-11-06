package service;

import dao.GeneratedValueDao;
import models.GeneratedValues;


public class GeneratedValueService {
    GeneratedValueDao generatedValueDao = new GeneratedValueDao();

    public GeneratedValues findGeneratedValueById(int id) {
        return generatedValueDao.findById(id);
    }

    public void createGeneratedValue(GeneratedValues generatedValues) {
        generatedValueDao.save(generatedValues);
    }

    public void deleteGeneratedValue(GeneratedValues generatedValues) {
        generatedValueDao.delete(generatedValues);
    }
}
