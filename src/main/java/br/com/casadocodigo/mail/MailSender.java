package br.com.casadocodigo.mail;

import org.springframework.mail.SimpleMailMessage;

public interface MailSender {
	void send(SimpleMailMessage message);
}
