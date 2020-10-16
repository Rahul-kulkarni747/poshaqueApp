package com.poshaque.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
	
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("poshaquehelp@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }
	
	public void sendMessageWithAttachment(String to, String subject, String text, File file) {
	    MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		    helper.setFrom("poshaquehelp@gmail.com");
		    helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(text);    
		    helper.addAttachment("Invoice", file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	    emailSender.send(message);
	}
}
