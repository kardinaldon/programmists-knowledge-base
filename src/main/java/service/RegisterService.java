package service;

import models.entity.GeneratedValues;
import models.entity.LetterTemplate;
import models.entity.user.RoleEnum;
import models.entity.user.StatusEnum;
import models.entity.user.User;


import java.util.concurrent.TimeUnit;

public class RegisterService {

    private User user = new User();
    private GeneratedValues generatedValues = new GeneratedValues();
    private LetterTemplate letterTemplate = new LetterTemplate();
    private LetterService letterService = new LetterService();
    private UserService userService = new UserService();
    private SendMailService sendMailService = new SendMailService();
    private GeneratedValueService generatedValueService = new GeneratedValueService();


    public void startRegistration (String email, String password) {

        user.setEmail(email);
        user.setPassword(password);
        user.setRole(RoleEnum.USER);
        user.setStatusEnum(StatusEnum.NOT_ACTIVE);
        userService.createUser(user);
        generatedValues = generatedValueService.createGeneratedValue(10,true,true);
        letterTemplate = letterService.findLetterById(1);
        sendMailService.sendMail(email,email.substring(0,email.indexOf("@")) + " " + letterTemplate.getLetterHeader(), letterTemplate.getLetterText() +
                "http://localhost:8080/com_programmists_knowledge_base_1_war/verification/email_verification_page.xhtml?id=" + generatedValues.getGeneratedValue() +
                "&email=" + email);
        try {
            TimeUnit.SECONDS.sleep(60);
            generatedValueService.deleteGeneratedValue(generatedValues);
            user = userService.findUserByEmail(email);
            if (user.getStatusEnum() == StatusEnum.NOT_ACTIVE) {
                userService.deleteUser(user);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
