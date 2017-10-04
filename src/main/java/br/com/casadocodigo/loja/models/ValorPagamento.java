package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class ValorPagamento {

    private BigDecimal value;
    
    public ValorPagamento(BigDecimal value) {
        this.value = value;
    }

    public ValorPagamento() {
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
