package br.com.casadocodigo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SpringMailSender implements br.com.casadocodigo.mail.MailSender {
	
	@Autowired
	private MailSender mailSender;

	@Override
	public void send(SimpleMailMessage message) {
		this.mailSender.send(message);
	}
}
