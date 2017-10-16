package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
			throw new DAOException("Erro ao ADICIONAR contato ao banco de dados \n Causa: "+e);
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
			throw new DAOException("Erro ao ALTERAR contato no banco de dados \n Causa: "+e);
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
			throw new DAOException("Erro ao EXCLUIR contato do banco de dados \n Causa: "+e);
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
			throw new DAOException("Erro ao buscar os contatos no banco de dados \n Causa: "+e);
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
			throw new DAOException("Erro ao BUSCAR o contato no banco de dados \n Causa: "+e);
		}
		
		return contato;
		
	}
	
	public List<Contato> buscarPorNome(String nome){
		
		String sql = "select * from contatos where nome like '%"+nome+"%'";
		
		try {
			
			ArrayList<Contato> contatos = new ArrayList<>();
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Contato contato = new Contato();
				
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				
				//BUSCAR DATA DE ANSCIMENTO
				Date data = rs.getDate("data_nascimento");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dataTexto=sdf.format(data);
				java.util.Date dataUtil=new java.util.Date();
				try {
					dataUtil=new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
				} catch (ParseException e) {
					System.out.println("Deu erro na conversão de data \n Causa: "+e);
				}
				Calendar dt = Calendar.getInstance();
				dt.setTime(dataUtil);
				contato.setData_nascimento(dt);
				
				contatos.add(contato);
				
			}
			
			rs.close();
			stmt.close();
			if(!contatos.isEmpty()){
				return contatos;
			}else {
					System.out.println("Nenhum nome encontrado com esses caracteres: "+nome);
					return contatos;
			}
			
			
		} catch (SQLException e) {

			throw new DAOException("Erro ao buscar contato pela Busca Por Nome. \n Motivo:"+e);
		}
}
	
	

}
