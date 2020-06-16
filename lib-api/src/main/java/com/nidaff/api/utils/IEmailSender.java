package com.nidaff.api.utils;

import com.nidaff.api.dto.BookDto;

import javax.mail.MessagingException;

public interface IEmailSender {

    void sendEmailToAdmin(BookDto dto, String departmentName) throws MessagingException;
    
}
