package org.bitmonsters.authserver.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.authserver.config.EmailConfig;
import org.bitmonsters.authserver.model.EmailVerificationToken;
import org.bitmonsters.authserver.model.PasswordResetToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailTrapEmailServiceImpl implements EmailService {

    @Value("${spring.mail.email}")
    private String email;

    private final EmailConfig emailConfig;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmail(String toMail, String subject, String body) {

        try {
            Session session = emailConfig.getMailSession();
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(email));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            Transport.send(mimeMessage);
        } catch (MessagingException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to an email", exception);
        }

    }

    @Override
    public void sendEmailWithTemplate(String toMail, String subject, String templateName, Map<String, Object> templateModel) {
        try {
            // create a mail session to send an email
            Session session = emailConfig.getMailSession();
            // set the email properties
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(email));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            mimeMessage.setSubject(subject);

            // process the Thymeleaf template
            Context context = new Context();
            context.setVariables(templateModel);
            String htmlBody = templateEngine.process(templateName, context);

            mimeMessage.setContent(htmlBody, "text/html");

            Transport.send(mimeMessage);
        } catch (MessagingException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to an email", exception);
        }

    }

    public void sendVerificationEmail(String email, EmailVerificationToken emailVerificationToken) {

        String subject = "BitBlogger User Signup Verification";
        // create the template model for the email we are sending
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put(
                "verificationUrl",
                String.format("http://localhost:9998/api/v1/auth/register/email/confirm?email=%s&code=%s",
                        emailVerificationToken.getUser().getEmail(),
                        emailVerificationToken.getToken()));

        //create mail body and send it using the sendMail method
        sendEmailWithTemplate(email, subject, "email-verification.html", templateModel);
    }

    public void sendPasswordResetEmail(String email, PasswordResetToken passwordResetToken) {

        String subject = "BitBlogger User Signup Verification";
        // create the template model for the email we are sending
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put(
                "resetUrl",
                String.format("http://localhost:9998/api/v1/auth/password/reset?email=%s&code=%s",
                        passwordResetToken.getUser().getEmail(),
                        passwordResetToken.getToken()));

        //create mail body and send it using the sendMail method
        sendEmailWithTemplate(email, subject, "password-reset.html", templateModel);
    }
}
