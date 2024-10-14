package org.bitmonsters.authserver.service;

import org.bitmonsters.authserver.model.EmailVerificationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface EmailService {

    void sendEmail(String toMail, String subject, String body);

    void sendEmailWithTemplate(String toMail, String subject, String templateName, Map<String, Object> templateModel);

}
