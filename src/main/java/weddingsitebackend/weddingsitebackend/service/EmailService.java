package weddingsitebackend.weddingsitebackend.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageHTMLtags(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException;
}
