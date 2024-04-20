package eduessence.iniciar_sesion.models.SendMail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Component
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleEmail(String email, String token, String username, String nombreApellido) {
        MimeMessage message = javaMailSender.createMimeMessage();

        String formattedDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        String content = MessageHTML.RECUPERAR_CLAVE
                .replace("{{fecha}}", formattedDateTime)
                .replace("((username))", username)
                .replace("{{nombreApellido}}", nombreApellido);

        try {

            message.setSubject("Código de recuperación de cuenta EDUESSENCE");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email.trim());

            int index = 0;
            for(int i=0; i<token.length(); i++){
                content = setCodeInTemplate(content, index, String.valueOf(token.charAt(i)));
                index++;
            }

            helper.setText(content, true);
            helper.setFrom(sender);
            javaMailSender.send(message);

        } catch (MessagingException ex) {
            throw new RuntimeException("Error sending email", ex);
        }
    }


    private String setCodeInTemplate(String templateCode, int index, String number){
        return templateCode.replace("{" + index + "}", number);
    }

}
