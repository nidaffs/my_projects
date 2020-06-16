package com.nidaff.rest.utils;

import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.utils.IEmailSenderToUser;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.StringWriter;

@Component
public class EmailSenderToUser implements IEmailSenderToUser {

    private static final String ADMIN_FROM_EMAIL_ADDRESS = "nidaff.s@gmail.com";

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Override
    public void sendEmailToUser(HistoryDto dto) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String text = prepareActivateRequestEmail(dto);
        configureMimeMessageHelper(helper, ADMIN_FROM_EMAIL_ADDRESS, dto.getUserEmail(), text, "Book return deadline!");
        mailSender.send(message);
    }
    
    private String prepareActivateRequestEmail(HistoryDto dto) {
        VelocityContext context = createVelocityContextWithBasicParameters(dto);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("mailtemplates/bookReturnDeadline.vm", "UTF-8", context, stringWriter);
        return stringWriter.toString();
    }

    private VelocityContext createVelocityContextWithBasicParameters(HistoryDto dto) {
        VelocityContext context = new VelocityContext();
        context.put("title", dto.getBookTitle());
        context.put("author", dto.getBookAuthor());
        return context;
    }

    private void configureMimeMessageHelper(MimeMessageHelper helper, String mailFrom, String mailTo, String mailText,
            String mailSubject) throws MessagingException {
        helper.setFrom(mailFrom);
        helper.setTo(mailTo);
        helper.setText(mailText, true);
        helper.setSubject(mailSubject);
    }

}
