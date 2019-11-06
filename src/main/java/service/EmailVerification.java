package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class EmailVerification {
    public void sendMail (String email, String idOfGeneratedValue) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(new File("src/main/resources/config.properties")));

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(props.getProperty("mail.smtp.login"), props.getProperty("mail.smtp.password"));
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kardinal021188@mail.ru"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Подтверждение регистрации");
            message.setText("Для подтверждения регистрации перейдите по ссылке");

            Transport.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
