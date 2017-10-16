package br.com.caelum.jdbc.dao;
// Crie uma classe DAO para Funcionario.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDAO {

	Connection con;
	
	public FuncionarioDAO(){
		con = new ConnectionFactory().getConnection();
	}
	
	// ADICIONA FUNCIONARO
	public void adiciona(Funcionario funcionario){
		
		String sql =  "insert into funcionarios" + "(nome, usuario, senha)" + "values (?, ?, ?)";
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
				throw new DAOException(e);
		}
		
	}
	
	// PESQUISA FUNCIONARIO
	public Funcionario pesquisa(int id_funcionarios){
		String sql = "select * from funcionarios where id = ?";
		Funcionario funcionario = new Funcionario();
		
		try {
			
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.setLong(1, id_funcionarios);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				
			}
			
			stmt.close();
			rs.close();
			return funcionario;
			
		} catch (SQLException e) {
				throw new DAOException(e);
		}
	}
	
	//REMOVER FUNCIONARIO
	public void remover(Funcionario funcionario){
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("delete from funcionarios where id = ?");
			stmt.setLong(1, funcionario.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	//BUSCA TODOS OS FUNCIONARIOS
	public List<Funcionario> getLista(){
		
		List<Funcionario> lista = new ArrayList<>();
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("select * from funcionarios");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				
				lista.add(funcionario);
			}
			
			stmt.close();
			rs.close();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}
}
