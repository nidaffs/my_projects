package com.nidaff.api.utils;

import com.nidaff.api.dto.HistoryDto;

import javax.mail.MessagingException;

public interface IEmailSenderToUser {

    void sendEmailToUser(HistoryDto dto) throws MessagingException;

}
