<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
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
<title>Lista Contatos</title>
</head>
<body>

	<%--Teste de comentário em JSP --%>
<table>
	<%
		String mensagem = "Lista de Contatos";
	%>
	
	<h1>
		<%
			out.println(mensagem);
		%>
	</h1>
	</br>

		<tr>
			
			<td><b>Nome</b></td>
			<td><b>E-Mail</b></td>
			<td><b>Endereço</b></td>
			<td><b>Nascimento</b></td>
			
		</tr>
		<%
			Contato con = new Contato();
			ContatoDAO dao = new ContatoDAO();

			List<Contato> contatos = dao.getLista();

			for (Contato contato : contatos) {
		%>
		
		<%
			
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		
		%>


		<tr>
			<td><%=contato.getNome()%></td>
			<td><%=contato.getEmail()%> </td>
			<td><%=contato.getEndereco()%> </td>
			<td><%=formato.format(contato.getData_nascimento().getTime())%> </td>
			

		</tr>

		<%
			}
		%>
		</table>
	</br>
	<div style="text-align: right;">
	<%
		String segMensagem = "Desenvolvido por: " + dao.pesquisa(1).getNome();
	%>
	<%=segMensagem%>
	</br>
	</div>
</body>
</html>