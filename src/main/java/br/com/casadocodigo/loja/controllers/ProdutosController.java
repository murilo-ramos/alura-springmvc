 package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.infra.FileSaver;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	private static final String PRODUTOS_VIEW_FORM = "produtos/form";
	private static final String PRODUTOS_VIEW_LISTA = "produtos/lista";
	private static final String PRODUTOS_VIEW_DETALHE = "produtos/detalhe";
	
	private static final String BASE_FILES_FOLDER = "arquivos-sumario";
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView view = new ModelAndView(PRODUTOS_VIEW_FORM);
		view.addObject("tipos", TipoPreco.values());		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(produto);
		}
		
		String sumarioPath = this.fileSaver.saveFileToDisk(BASE_FILES_FOLDER, sumario);
		
		produto.setSumarioPath(sumarioPath);		
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
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView view = new ModelAndView(PRODUTOS_VIEW_DETALHE);
		
		Produto produto = this.produtoDao.getProdutoById(id);
		view.addObject("produto", produto);
		
		return view;
	}
}
