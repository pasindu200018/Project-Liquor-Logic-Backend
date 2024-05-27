package com.liquorlogic.userservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendResetPasswordEmail(String to, int resetToken) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Password Reset Request");

        String emailBody = "<p>Hello,</p>"
                + "<p>You have requested to reset your password. Click the link below to reset your password:</p>"
                + "<a href='http://your-app-url/reset-password?token=" + resetToken + "'>Reset Password</a>"
                +"<h1>Your Code Is - "+resetToken+"</h1>"
                + "<p>If you did not request this, please ignore this email.</p>";
        helper.setText(emailBody, true);

        javaMailSender.send(message);
    }
}
