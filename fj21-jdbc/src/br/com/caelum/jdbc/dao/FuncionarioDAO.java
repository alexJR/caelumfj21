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
	
	public void adicionar(Funcionario funcionario){
		
		String sql = "insert into funcionarios (nome, usuario, senha) "
				+ "values(?, ?, ?) ";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			
			stmt.execute();
			System.out.println("Funcionário "+funcionario.getNome()+" inserido com sucesso!");
			con.close();
			
		} catch (SQLException e) {

			throw new DAOException("Erro ao inserir Funcionário no BD \n Motivo do erro: " + e);
		}
		
	}
	
	public void alterar(Funcionario funcionario){
		
		String sql = "update funcionarios set nome = ?, usuario = ?, senha = ?"
				+ "where id = ?";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.setLong(4, funcionario.getId());
			
			stmt.execute();
			System.out.println("Funcionário " + funcionario.getNome() + " alterado com sucesso!");
			con.close();
			
		} catch (SQLException e) {

			throw new DAOException("Erro ao alterar contato na DAO \n Motivo: " + e);
		}
		
		
	}
	
	public void deletar(Funcionario funcionario){
		
		try {
			PreparedStatement stmt = con.prepareStatement("delete from funcionarios where id = ?");
			
			stmt.setLong(1, funcionario.getId());
			
			stmt.execute();
			System.out.println("Funcionário " +funcionario.getNome() + " excluído com sucesso!");
			con.close();
			
		} catch (SQLException e) {

			throw new DAOException("Erro ao deletar funcionario no DAO \n Motivo: " + e);
		}
		
		
	}
	
	public Funcionario buscarPorId(int id){

		String sql = "select * from funcionarios where id = "+id;
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			Funcionario funcionario = new Funcionario();
			
			funcionario.setId(rs.getLong("id"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setUsuario(rs.getString("usuario"));
			funcionario.setSenha(rs.getString("senha"));
			
			rs.close();
			System.out.println(funcionario.toString());
			return funcionario;
			
			
			
		} catch (SQLException e) {

			throw new DAOException("Erro ao buscar funcionario no BD \n Motivo: " + e);
		}
	
	}
	
	public List<Funcionario> getLita(){
		
		String sql = "select * from funcionarios";
		
		try {
			
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				
				funcionarios.add(funcionario);
			}
			
			rs.close();
			con.close();
			if(!funcionarios.isEmpty()){
				return funcionarios;
			}else {
					System.out.println("Nenhum funcionário encontrado!");
					return funcionarios;
			}
			
			
		} catch (SQLException e) {

			throw new DAOException("Erro ao buscar os funcionarios no BD \n Motivo: " + e);
		}
				
	}
	
	public List<Funcionario> buscarPorNome(String nome){
		
		String sql = "select * from funcionarios where nome like '%"+nome+"%'";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				
				funcionarios.add(funcionario);
				
			}
			
			rs.close();
			stmt.close();
			con.close();
			if(!funcionarios.isEmpty()){
				return funcionarios;
			}else {
					System.out.println("Nenhum nome encontrado com esses caracteres: "+nome);
					return funcionarios;
			}
			
			
		} catch (SQLException e) {

			throw new DAOException("Erro ao buscar os funcionarios por nome/filtro no BD \n Motivo: " + e);
		}
}
}
