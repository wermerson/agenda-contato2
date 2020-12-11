<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:admin_template title="Contatos" breadcrumb="Dashboard">
	
	<jsp:attribute name="content">
		
		<section class="content">
		<c:if test="${not empty response}">
	 		<div class="col-lg-12 mb-4">
				<div class="card bg-success text-white shadow">
	                 <div class="card-body">
	                     <div class="text-white-50"><c:out value="${response}"/></div>
	                 </div>
	             </div>
			</div>
		</c:if>
		
		<div class="col-lg-12 mb-4 text-right">
	     	<a class="btn btn-primary" href="${pageContext.request.contextPath}/pages/cadastros/contatos/cadastro.jsp">Novo</a>
		</div>
	
	     <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Lista de Contatos</h6>
            </div>
            
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Nome</th>
                      <th>E-mail</th>
                      <th>Telefone</th>
                      <th>Status</th>
                      <th>Ações</th>
                    </tr>
                  </thead>
                 
                  <tbody>
                    <c:forEach var="c" items="${contatos}">
                    	<tr>
	                    	<td>
	                    	  <c:out value="${c.nome}"/>
	                    	</td>
	                    	<td>
	                    	  <c:out value="${c.email}"/>	
	                    	</td>
	                    	<td>
	                    	  <c:out value="${c.telefone}"/>	
	                    	</td>
	                    	<td>
	                    	  <c:out value="${c.ativo ? 'Ativo' : 'Inativo'}"/>	
	                    	  
	                    	</td>
	                    	<td>
	                    		<a href ="${pageContext.request.contextPath}/contatoControllerServlet?id=${c.id}&action=ativar" class="btn btn-danger">Ativar</a>
	                    		
	                    		<a href="${pageContext.request.contextPath}/contatoControllerServlet?id=${c.id}&action=editar" class="btn btn-primary btn-circle"><i class="fas fa-pencil-alt"></i></a> 
	                    		<a href="${pageContext.request.contextPath}/contatoControllerServlet?id=${c.id}&action=delete" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a> 
	                    	</td>
                    	</tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
     	
     </section>
					
	</jsp:attribute>
	
</mt:admin_template>
