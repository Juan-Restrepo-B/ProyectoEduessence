package eduessence.registro_cliente.models.SendMail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
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

    public void sendSimpleEmailUser(String email, String username, String nombreApellido) {
        MimeMessage message = javaMailSender.createMimeMessage();

        String formattedDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        String content = MessageHTML.CREACION_USUARIO
                .replace("{{fecha}}", formattedDateTime)
                .replace("((username))", username)
                .replace("{{nombreApellido}}", nombreApellido);

        try {

            String displayName = "EDUESSENCE";
            String fromAddress = sender;
            message.setSubject("Bienvenido a la Familia EDUESSENCE");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email.trim());
            helper.setText(content, true);
            helper.setFrom(new InternetAddress(fromAddress, displayName));
            javaMailSender.send(message);

        } catch (MessagingException ex) {
            throw new RuntimeException("Error sending email", ex);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSimpleEmailEmpl(String email, String username, String password, String rol, String nombreApellido) {
        MimeMessage message = javaMailSender.createMimeMessage();

        String formattedDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        String content = MessageHTML.CREACION_EMPLEADO
                .replace("{{fecha}}", formattedDateTime)
                .replace("((username))", username)
                .replace("((password))", password)
                .replace("((rol))", rol)
                .replace("{{nombreApellido}}", nombreApellido);

        try {

            String displayName = "EDUESSENCE";
            String fromAddress = sender;
            message.setSubject("Bienvenido a la Familia EDUESSENCE");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email.trim());
            helper.setText(content, true);
            helper.setFrom(new InternetAddress(fromAddress, displayName));
            javaMailSender.send(message);

        } catch (MessagingException ex) {
            throw new RuntimeException("Error sending email", ex);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}