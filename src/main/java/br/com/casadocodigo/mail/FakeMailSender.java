package br.com.casadocodigo.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class FakeMailSender implements MailSender {

	@Override
	public void send(SimpleMailMessage message) {
		System.out.println(message);
	}

}
