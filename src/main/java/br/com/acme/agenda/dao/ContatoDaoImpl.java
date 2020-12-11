/**
 * 
 */
package br.com.acme.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.acme.agenda.model.Contato;
import br.com.acme.agenda.utils.JPAUtil;
import br.com.acme.agenda.model.Usuario;
//import br.com.acme.agenda.service.ServiceLogin;

/**
 * @author TERCEIRA CAMADA
 *
 */
public class ContatoDaoImpl implements ContatoDao {

	@Override
	public void salvar(Contato contato) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(contato);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<Contato> listarContatos() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		System.out.println("1-"+entityManager.isOpen());
	
		System.out.println("2-"+entityManager.isOpen());
		Query consulta = entityManager.createQuery("SELECT c FROM Contato c");
		List<Contato> listaDeContatosDoBancoDeDados = consulta.getResultList();
		entityManager.close();
		System.out.println("3-"+entityManager.isOpen());
		return listaDeContatosDoBancoDeDados;
	}

	@Override
	public void remover(Long idContato) {
		try {
			EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager.getTransaction().begin();
			Contato contato = entityManager.find(Contato.class, idContato);
			entityManager.remove(contato);
			entityManager.getTransaction().commit();
			entityManager.close();
		}catch(NoResultException e){
			e.getMessage();
			
		}
	}

	@Override
	public Contato buscarPorIdContato(Long idContato) {
		
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Contato contato = entityManager.find(Contato.class, idContato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return contato;
	}

	@Override
	public void editarContato(Long idContato, Contato contato) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Contato contatoQueTavaNoBanco = entityManager.find(Contato.class, idContato);
		contatoQueTavaNoBanco.setAtivo(contato.isAtivo());
		contatoQueTavaNoBanco.setEmail(contato.getEmail());
		contatoQueTavaNoBanco.setTelefone(contato.getTelefone());
		contatoQueTavaNoBanco.setNome(contato.getNome());
		
		entityManager.merge(contatoQueTavaNoBanco);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Override
	public Contato buscaContatoPorEmail(String email) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			return entityManager.createNamedQuery("Contato.buscaContatoPorEmail", Contato.class)
					.setParameter("email", email)
					.getSingleResult();
		}catch(NoResultException e){
			e.getMessage();
		}
		return null;
	}

	@Override
	public void ativarDesativarContato(Long id) {
		
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Contato contatoQueTavaNoBanco = entityManager.find(Contato.class, id);
		if(contatoQueTavaNoBanco != null) {
			if (contatoQueTavaNoBanco.isAtivo()) {
				contatoQueTavaNoBanco.setAtivo(false);
			} else {
				contatoQueTavaNoBanco.setAtivo(true);
			}
			
		}
		entityManager.merge(contatoQueTavaNoBanco);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	//verificar e adicionar usuario
	public static List<Usuario> usuarios = new ArrayList<Usuario>();
	public static boolean login(String login, String senha) {
		for(Usuario usuario : usuarios) {
			if((usuario.getLogin().equals(login)) && (usuario.getSenha().equals(senha))) {
				return true;
			}
	
		}
		return false;
		
	}
	public static void adicionarUsuario() {
		if (!login("angelo@hotmail.com", "123")) {
			usuarios.add(new Usuario("angelo","123"));
		}
	}
	
	
}










