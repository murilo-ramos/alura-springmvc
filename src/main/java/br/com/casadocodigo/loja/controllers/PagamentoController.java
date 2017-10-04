package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.ValorPagamento;
import br.com.casadocodigo.cart.CarrinhoCompras;
import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
    private static final String PAYMENT_URI_SERVICE = "http://book-payment.herokuapp.com/payment";
    
    @Autowired
    private CarrinhoCompras carrinhoCompras;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public Callable<String> finalizar(RedirectAttributes redirectAttributes) {
        return () -> {
            ValorPagamento value = new ValorPagamento(this.carrinhoCompras.getPrecoTotal());
        
        try {
            String result = this.restTemplate.postForObject(PAYMENT_URI_SERVICE, value, String.class);
            redirectAttributes.addFlashAttribute("sucesso", result);
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getMessage());
            redirectAttributes.addFlashAttribute("falha", "Valor acima do permitido");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            redirectAttributes.addFlashAttribute("falha", "Erro não esperado");
        }
        
        
        return "redirect:/produtos";
        };
    }
}
