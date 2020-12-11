package br.com.acme.agenda.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.acme.agenda.model.Contato;
import br.com.acme.agenda.service.ContatoService;
import br.com.acme.agenda.service.ContatoServiceImpl;
import br.com.acme.agenda.utils.Constantes;

/**
 * Servlet implementation class ContatoControllerServlet 
 * PRIMEIRA CAMADA
 */
@WebServlet("/contatoControllerServlet")
public class ContatoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Contato contato;
	private ContatoService service;
	private List<Contato> contatos;
	private Contato contatoEditar;
	public ContatoControllerServlet() {
		this.contato = new Contato();
		this.service = new ContatoServiceImpl();
		this.contatos = new ArrayList<Contato>();
		this.contatoEditar = new Contato();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("Constantes", Constantes.class);
		
		
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		
		List <Contato> listaContatos = this.service.listarContatos();
		//Contato lista = this.contatos;
		if (id != null) {
			if (action != null) {
				switch (action) {
					case "delete":
						try {
							this.service.remover(Long.parseLong(id));
							request.setAttribute("response", "Contato deletado com Sucesso.");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							request.setAttribute("response", "");
						}
						
						break;
					case "editar":
							
						try {
							this.contato = this.service.buscarPorIdContato(Long.parseLong(id));
							RequestDispatcher rd = request.getRequestDispatcher(Constantes.CADASTRO_CONTATO);
							request.setAttribute("contato", this.contato);
							rd.forward(request, response);
						} catch (Exception e) {
							System.out.println(e.getMessage());
							request.setAttribute("response", "Não foi possível editar esse contato.");
						}
						
						break;
					case "ativar": 
						this.service.ativarDesativarContato(Long.parseLong(id)); 
						request.setAttribute("response", "Contato desativado/ativado com sucesso.");
						break;
					default:
						break;
				}
			}
		}
		
		this.contatos = this.service.listarContatos();
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.LISTAR_CONTATOS);
		request.setAttribute("contatos", this.contatos);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Instanciar o contato
		this.contato = new Contato();
		
		//recuperar dados do request / formulário
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		
		
		
		//setar os atributos na instancia de contato
		this.contato.setNome(nome);
		this.contato.setEmail(email);
		this.contato.setTelefone(telefone);
		this.contato.setAtivo(true);
		this.contato.isAtivo();
		if (id != null && id != "") {
			this.service.editarContato(Long.parseLong(id), this.contato);
			request.setAttribute("response", "Contato atualizado com sucesso.");
		} else {
			this.service.salvar(this.contato);
			request.setAttribute("response", "Contato cadastrado com sucesso.");
		}
		
		
		
		
		this.doGet(request, response);
		
		//após salvar, irá redirecionar para a listagem
		//response.getWriter().append("<script> location.href='contatoControllerServlet?resposta=sucesso';</script>");
		
		//if(!validEmail(email)) {
			//chama a camada de service para salvar o contato
			//this.service.salvar(this.contato);
			//após salvar, redireciona para a lista de contatos
			//request.setAttribute("sucesso", "Contato" + nome + " cadastrado com sucesso");
		//}else {
			request.setAttribute("contatoExiste", "Contato com e-mail" + email + "já existe");	
		//}
		
		//doGet(request, response);
		
	}

	//private boolean validEmail(String email) {
	//	if(this.service.buscaPorEmail(email) != null) {
	//		return true;
	//	}else {
	//		return false;
	//	}
	//}
}







