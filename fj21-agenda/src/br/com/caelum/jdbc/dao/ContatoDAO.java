package br.com.caelum.jdbc.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

/* Vamos desenvolver nossa classede DAO.
 * Crie a classe ContatoDao no pacotebr.com.caelum.jdbc.dao.
 * Seu papel será gerenciar a conexão e inserir Contatos no banco de dados*/
public class ContatoDAO {
	
	private Connection con;
	
	// Agora toda vez que instanciar essa classe, uma conexao sera aberta
	public ContatoDAO(){
		this.con =  new ConnectionFactory().getConnection();
	}
	
	// Metodo adionar contato
	public void adiciona(Contato contato){
		// salva na variavel SQL o Quary para adicionar tuplas no Banco de dados (MySQL)
		String sql = "insert into contatos" + "(nome, email, endereco, data_nascimento)" +
					"values (?, ?, ?, ?)"; //(1, 2, 3, 4)
		
		try {
			// Converte a String de um forma que o BD reconheça 
			PreparedStatement stmt =  con.prepareStatement(sql);
			
			
			// setando os valores para cada atributo
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getData_nascimento().getTimeInMillis()));
			
			// Executa os comendos acima
			stmt.execute();
			// Fecha a conexao com o banco
			stmt.close();
		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	// Metodo atualiza contato
	public void altera(Contato contato){
		
		String sql = "update contatos set nome = ?, email = ?, endereco = ?, "
				+ "data_nascimento = ? where id = ?";
		
		try {
			
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getData_nascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	//REMOVER CONTATO
	public void remover(Contato contato){
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("delete from contatos where id = ?");
			stmt.setLong(1, contato.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}
	
	// Metodo pegar a lista de contatos
	// Como vai retornar uma lista, o metode deve usar o List do java.util
	public List<Contato> getLista(){
			List<Contato> contatos = new ArrayList<Contato>();
		
		try {
			// cria a lista que vai receber os registro do BD
			
			
			// Faz a query para pegar todos os contatos
			PreparedStatement stmt = this.con.prepareStatement("select * from contatos");
			
			// Executa a query e vai restornar um Resultset(todos os registro)
			ResultSet rs = stmt.executeQuery();
			
			// Mostrar todos os regitros cada volta do while enquanto acabar(ate que for false)
			//O next retorna false quando nao tem masi registro, ai para o while
			while(rs.next()){

				// Criar um contato
				Contato con = new Contato();
				con.setId(rs.getLong("id"));
				con.setNome(rs.getString("nome"));
				con.setEmail(rs.getString("email"));
				con.setEndereco(rs.getString("endereco"));
				
				//Pegar a data é um pouco masi chato
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				con.setData_nascimento(data);
				
				// adicionar os objetos na lista
				contatos.add(con);
				
			}
			// Fechar o Resultset
			rs.close();
			// Fechar o PreparedStantement
			stmt.close();
			// retornar a lista com todos os registros adicionados
			return contatos;
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	// Crie o método pesquisar que recebe um id(int)e retorna um objeto do tipo	Contato
	public Contato pesquisa(int id_Contato) {
		Contato contato = new Contato();
		
		try {
			
			String sql = "select * from contatos where id = ?";
			
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.setInt(1, id_Contato);
			
			ResultSet rs = stmt.executeQuery();			
			
			while(rs.next()){
		
			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));
			
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_nascimento"));			
			contato.setData_nascimento(data);
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return contato;
		
	}
	
	

}
