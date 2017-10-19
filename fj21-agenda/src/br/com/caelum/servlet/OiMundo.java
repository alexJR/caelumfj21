package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletOiMundoA", urlPatterns = {"/oi","/ola"})
public class OiMundo extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Primeira Servelet </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> Olá Mundo Servelet! </h1>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
