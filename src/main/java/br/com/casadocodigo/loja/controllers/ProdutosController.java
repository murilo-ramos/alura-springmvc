 package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	private static final String PRODUTOS_VIEW_FORM = "produtos/form";
	private static final String PRODUTOS_VIEW_OK = "produtos/ok";
	private static final String PRODUTOS_VIEW_LISTA = "produtos/lista";
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView(PRODUTOS_VIEW_FORM);
		view.addObject("tipos", TipoPreco.values());		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(Produto produto, RedirectAttributes redirectAttributes) {
		System.out.println(produto.toString());		
		this.produtoDao.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto gravado com sucesso !!!");
		return new ModelAndView("redirect:/produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();

		ModelAndView view = new ModelAndView(PRODUTOS_VIEW_LISTA);
		view.addObject("produtos", produtos);
		
		return view;
	}
}
