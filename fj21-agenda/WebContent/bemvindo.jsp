<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.caelum.jdbc.dao.ContatoDAO"%>
<%@page import="br.com.caelum.jdbc.modelo.Contato"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bem Vindo</title>
</head>
<body>

		<%--Teste de comentário em JSP --%>
		
		<%
			String mensagem = "Bem vindo ao sistema de agenda do FJ-21!" ;
		%>
		<h1>
		<% out.println(mensagem); %>
		</h1>
		</br>
	
	<%
		String segMensagem = "Desenvolvido por: Alex Rampanelli";
	%>
	<%= segMensagem %>
	</br>
	<%
		System.out.println("Tudo foi executado!");
	%>
</body>
</html>