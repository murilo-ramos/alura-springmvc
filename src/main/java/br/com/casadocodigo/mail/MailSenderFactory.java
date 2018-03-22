package br.com.casadocodigo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailSenderFactory {
	
	@Autowired
	private SpringMailSender springMailSender;
	
	@Autowired
	private FakeMailSender fakeMailSender;
	
	public MailSender create() {
		return this.fakeMailSender;
	}

}
