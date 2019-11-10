package service;

import dao.SessionDAO;
import models.user.SessionEntity;

public class HttpSessionService {

    private SessionDAO sessionDAO = new SessionDAO();

    public SessionEntity findSessionEntityByValue(String value) throws InterruptedException {
        return sessionDAO.findByValue(value);
    }

    public SessionEntity findSessionById(int id) {
        return sessionDAO.findById(id);
    }

    public void saveSession(SessionEntity sessionEntity) {
        sessionDAO.save(sessionEntity);
    }

    public void deleteSession(SessionEntity sessionEntity) {
        sessionDAO.delete(sessionEntity);
    }

}
