package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuarios = this.entityManager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
												   .setParameter("email", username)
												   .getResultList();
		
		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado");
		}		
		
		return usuarios.get(0);
	}
	

}
