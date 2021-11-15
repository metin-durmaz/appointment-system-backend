package com.turksat.appointmentsystem.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {

    void infoMail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException;
    void registerMail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException;

}
