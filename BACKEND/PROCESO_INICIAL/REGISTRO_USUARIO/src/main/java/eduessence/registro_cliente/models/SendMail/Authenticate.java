package eduessence.registro_cliente.models.SendMail;

import org.springframework.stereotype.Service;

@Service
public class Authenticate {
    private final EmailService mailSender;
    public Authenticate(EmailService mailSender) {
        this.mailSender = mailSender;
    }
    public void sendMensaggerUser(String email, String username, String nombreApellido){
        mailSender.sendSimpleEmailUser(email, username, nombreApellido);
    }
    public void sendMensaggerEmpl(String email, String username, String password, String rol, String nombreApellido){
        mailSender.sendSimpleEmailEmpl(email, username, password, rol, nombreApellido);
    }
}
