package eduessence.iniciar_sesion.models.SendMail;

import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class Authenticate {
    private final EmailService mailSender;

    public Authenticate(EmailService mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMensagger(String email, String tokem, String username, String nombreApellido){
        mailSender.sendSimpleEmail(email, tokem, username, nombreApellido);
    }
}