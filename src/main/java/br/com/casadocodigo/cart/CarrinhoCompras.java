package br.com.casadocodigo.cart;

import br.com.casadocodigo.loja.models.CarrinhoItem;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras {
    private Map<CarrinhoItem, Integer> carrinhoItems = new LinkedHashMap<>();
    
    public void addCarrinhoItem(CarrinhoItem carrinhoItem) {
        if (this.carrinhoItems.containsKey(carrinhoItem)) {
            Integer quantidade = this.carrinhoItems.get(carrinhoItem);            
            this.carrinhoItems.replace(carrinhoItem, quantidade, quantidade++);
        } else {
            this.carrinhoItems.put(carrinhoItem, 1);
        }
    }
    
    public int getQuantidade() {
        return this.carrinhoItems.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
    }
}
