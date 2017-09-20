 package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public String gravar(Produto produto) {
		System.out.println(produto.toString());
		
		this.produtoDao.gravar(produto);
		
		return PRODUTOS_VIEW_OK;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();

		ModelAndView view = new ModelAndView(PRODUTOS_VIEW_LISTA);
		view.addObject("produtos", produtos);
		
		return view;
	}
}
