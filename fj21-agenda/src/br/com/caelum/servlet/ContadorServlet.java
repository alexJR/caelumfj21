package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contador")
public class ContadorServlet extends HttpServlet{
	
	private int contador = 0;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		log("Carregando as configurações da Servlet");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		log("Destruido a Servlet ");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {

		contador ++;
		
		PrintWriter out = respose.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Contador da Servlet </title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<h1> Contador agora é: " + contador + "</h1>");
		
		out.println("</body>");
		out.println("</html>");
		
	}

}
