<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/admin/dashboard/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Agenda <sup>acme</sup></div>
      </a>

      <hr class="sidebar-divider my-0">

      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard/index.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Home</span></a>
      </li>


	  <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/contatoControllerServlet">
          <i class="fas fa-fw fa-users"></i>
          <span>Contatos</span></a>
      </li>
      
    </ul>