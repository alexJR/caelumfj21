package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.dao.DAOException;
import br.com.caelum.jdbc.modelo.Contato;

//Mapeando a minha classe servlet para acessar pela URL do anvegador
@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		//Pegando os dados que serão inseridos na pagina/formulario 
		// em HTML adiciona-contato.html
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		
		//Pegar a data em String e converter para um objeto do tipo Calendar
		String dataEmTexto = request.getParameter("dataNascimento");
		
		Calendar dataNascimento = null;
		
		try {
			
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(data);
			
		} catch (java.text.ParseException e) {

			//out.println("Erro durante a conversão da data na servlet adicionaContato!");
			throw new DAOException("Erro durante a conversão da data na servlet adicionaContato \n Motivo: "+e);
			

		}
		
		//Instanciando e inserindo os dados na variaveis do objeto contato
		Contato contato = new Contato();
		
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setData_nascimento(dataNascimento);
		
		//Instanciar o objeto contatoDAO para adicionar no BD
		ContatoDAO contatoDao = new ContatoDAO();
		contatoDao.adiciona(contato);
		
		//Passar um resposta/feedback para o usuário informando que o contato foi adiciona com sucesso
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Adiciona Contato </title>");
		out.println("<head>");
		out.println("<body>");
		out.println("<h1> Contato " + contato.getNome() + " inserido com sucesso! </h1>");
		out.println("</body>");
		out.println("</html>");

		
	}

}
