package br.com.casadocodigo.cart;

import br.com.casadocodigo.loja.models.CarrinhoItem;
import java.math.BigDecimal;
import java.util.Collection;
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
    
    public Collection<CarrinhoItem> getItens() {
        return this.carrinhoItems.keySet();
    }
    
    public Integer getQuantidadeByCarrinhoItem(CarrinhoItem item){
        return this.carrinhoItems.get(item);
    }
    
    public BigDecimal getPrecoByCarrinhoItem(CarrinhoItem item) {
        return item.getPreco().multiply(new BigDecimal(this.carrinhoItems.get(item)));
    }
    
    public Integer getQuantidade() {
        return this.carrinhoItems.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
    }
    
    public BigDecimal getPrecoTotal() {
        BigDecimal total = BigDecimal.ZERO;
        
        for (CarrinhoItem item : this.carrinhoItems.keySet()) {
            total = total.add(this.getPrecoByCarrinhoItem(item));
        }
        
        return total;
    }
}
