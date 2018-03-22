package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void gravar(Produto produto) {
		this.entityManager.persist(produto);
	}

	public List<Produto> listar() {
		return this.entityManager.createQuery("select distinct(p) from Produto p join fetch p.precos", Produto.class).getResultList();
	}

	public Produto getProdutoById(Integer id) {
		return this.entityManager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public BigDecimal somePrecosPorTipoPreco(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = this.entityManager.createQuery("select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipoPreco", BigDecimal.class)
														 .setParameter("tipoPreco", tipoPreco);
		
		return query.getSingleResult();
	}
}
