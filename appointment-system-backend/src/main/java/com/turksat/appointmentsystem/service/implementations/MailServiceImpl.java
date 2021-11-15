package com.turksat.appointmentsystem.service.implementations;

import com.turksat.appointmentsystem.service.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * It sends email about appointment details.
     *
     * @param recipientEmail
     * @param link
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Override
    public void infoMail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("metin18286@gmail.com", "ClubInn Team");
        helper.setTo(recipientEmail);

        String subject = "Welcome !!";

        String content = "<p> Hello </p>"
                + "<p><a href=\"" + link + "\">Login</a></p>";
        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    /**
     * It sends email to new registered users.
     *
     * @param recipientEmail
     * @param link
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Override
    public void registerMail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("clubinnnteam@gmail.com", "AppointmentSystem Team");
        helper.setTo(recipientEmail);

        String subject = "Welcome to the ClubIn!!";

        String content = "<p>Hello,</p>"
                + "<p>Welcome to the AppointmentSystem!</p>"
                + "<p>You just registered to the our system!</p>"
                + "<p>You can use the following link to login and enjoy!:</p>"
                + "<p><a href=\"" + link + "\">Login</a></p>"
                + "                     <p><b>AppointmentSystem Team<b></p>"
                + "<br>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
