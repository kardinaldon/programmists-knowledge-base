package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SendMailService {
    public void sendMail (String email, String topicOfTheLetter, String text) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(new File("/home/cardinal/programmists-knowledge-base/src/main/resources/config.properties")));

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(props.getProperty("mail.smtp.login"), props.getProperty("mail.smtp.password"));
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("mail.smtp.login")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(topicOfTheLetter);
            message.setText(text);

            Transport.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
