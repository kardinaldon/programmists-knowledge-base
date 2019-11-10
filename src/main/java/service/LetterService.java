package service;

import dao.LetterDAO;
import models.LetterTemplate;

import java.util.List;

public class LetterService {

    private LetterDAO letterDAO  = new LetterDAO();

    public LetterTemplate findLetterById(int id) {
        return letterDAO.findById(id);
    }

    public void createLetter(LetterTemplate letterTemplate) {
        letterDAO.save(letterTemplate);
    }

    public void deleteLetter(LetterTemplate letterTemplate) {
        letterDAO.delete(letterTemplate);
    }

    public void deleteAllUsers() {
        letterDAO.deleteAll();
    }

    public void updateLetter(LetterTemplate letterTemplate) {
        letterDAO.update(letterTemplate);
    }

    public List<LetterTemplate> findAllLetters() {
        return letterDAO.findAll();
    }
}
