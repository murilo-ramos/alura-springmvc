package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private static final String HOME_VIEW ="home";

    @Autowired
    private ProdutoDAO produtoDao;

    @RequestMapping("/")
    @Cacheable(value = "produtosHome")
    public ModelAndView index() {
        List<Produto> produtos = produtoDao.listar();

        ModelAndView view = new ModelAndView(HOME_VIEW);
        view.addObject("produtos", produtos);

        return view;
    }
}
