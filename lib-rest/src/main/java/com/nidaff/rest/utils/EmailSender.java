package com.nidaff.rest.utils;

import com.nidaff.api.dto.BookDto;
import com.nidaff.api.utils.IEmailSender;

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
public class EmailSender implements IEmailSender {

	private static final String ADMIN_FROM_EMAIL_ADDRESS = "nidaff@mail.ru";

	private static final String ADMIN_TO_EMAIL_ADDRESS = "nidaff@mail.ru";

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Async
	@Override
	public void sendEmailToAdmin(BookDto dto, String departmentName) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String text = prepareActivateRequestEmail(dto, departmentName);
		configureMimeMessageHelper(helper, ADMIN_FROM_EMAIL_ADDRESS, ADMIN_TO_EMAIL_ADDRESS, text, "New Book!");
		mailSender.send(message);
	}

	private String prepareActivateRequestEmail(BookDto dto, String departmentName ) {
		VelocityContext context = createVelocityContextWithBasicParameters(dto, departmentName);
		StringWriter stringWriter = new StringWriter();
		velocityEngine.mergeTemplate("mailtemplates/newBookMessage.vm", "UTF-8", context, stringWriter);
		return stringWriter.toString();
	}

	private VelocityContext createVelocityContextWithBasicParameters(BookDto dto, String departmentName) {
		VelocityContext context = new VelocityContext();
		context.put("title", dto.getBookDetails().getTitle());
		context.put("author", dto.getBookDetails().getAuthor());
		context.put("department", departmentName);
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
