package br.com.casadocodigo.loja.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView error(Exception exception){
		LOGGER.error(exception.getMessage());
		
		ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
	}
}
