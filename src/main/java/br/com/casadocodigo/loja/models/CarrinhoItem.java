package br.com.casadocodigo.loja.models;

import java.util.Objects;

public class CarrinhoItem {

    private Produto produto;
    private TipoPreco tipoPreco;

    public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
        this.produto = produto;
        this.tipoPreco = tipoPreco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TipoPreco getTipoPreco() {
        return tipoPreco;
    }

    public void setTipoPreco(TipoPreco tipoPreco) {
        this.tipoPreco = tipoPreco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.produto);
        hash = 89 * hash + Objects.hashCode(this.tipoPreco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarrinhoItem other = (CarrinhoItem) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (this.tipoPreco != other.tipoPreco) {
            return false;
        }
        return true;
    }
    
    
}
