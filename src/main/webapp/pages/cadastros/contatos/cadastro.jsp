<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ page import="br.com.acme.agenda.service.*" %>


<mt:admin_template title="Contatos" breadcrumb="Dashboard">

	<jsp:attribute name="content">
     	
     	<div class="container-fluid">
			<section class="content">
			
			     <div class="card shadow mb-4">
		            <div class="card-header py-3">
		              <h6 class="m-0 font-weight-bold text-primary">Cadastro de Contato</h6>
		            </div>
		            
		            <div class="card-body">
		              <form class="user" action="${pageContext.request.contextPath}/contatoControllerServlet" method="post">
				     	<input type="hidden" name="id" value="<c:out value="${contato.id}"/>"/>
				     	<div class="form-group">
						    <label>Nome</label>
						    <input type="text" class="form-control" name="nome" value="<c:out value="${contato.nome}"/>"/>

						  </div>
						  <div class="form-group">
						    <label>Email</label>
						    <input type="email" class="form-control" name="email" value="<c:out value="${contato.email}"/>">
						  </div>
						  <div class="form-group">
						    <label>Telefone</label>
						    <input type="text" class="form-control" name="telefone" value="<c:out value="${contato.telefone}"/>">
						  </div>
						  <button type="submit" class="btn btn-primary">Salvar</button>
						</form>
		            </div>
		          </div>
		     	
		     </section>
		</div>
					
	</jsp:attribute>

</mt:admin_template>
