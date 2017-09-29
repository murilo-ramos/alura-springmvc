package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void gravar(Produto produto) {
		this.entityManager.persist(produto);
	}

	public List<Produto> listar() {
		return this.entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto getProdutoById(Integer id) {
		return this.entityManager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
