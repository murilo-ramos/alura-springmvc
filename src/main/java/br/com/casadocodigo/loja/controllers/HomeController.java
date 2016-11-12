package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final String HOME_VIEW ="home";

	@RequestMapping("/")
	public String index() {
		System.out.println("Exibindo a Home da Casa do Codigo");
		return HOME_VIEW;
	}
	
}
