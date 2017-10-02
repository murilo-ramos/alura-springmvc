package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.cart.CarrinhoCompras;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
    
    @Autowired
    private ProdutoDAO produtoDAO;
    
    @Autowired
    private CarrinhoCompras carrinhoCompras;
    
    @RequestMapping("/add")
    public String add(Integer produtoId, TipoPreco tipoPreco) {
        Produto produto   = this.produtoDAO.getProdutoById(produtoId);
        CarrinhoItem item = new CarrinhoItem(produto, tipoPreco);        
        this.carrinhoCompras.addCarrinhoItem(item);
        return "redirect:/produtos";
    }
    
}
