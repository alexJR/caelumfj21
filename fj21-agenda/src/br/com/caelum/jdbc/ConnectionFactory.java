package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.caelum.jdbc.dao.DAOException;

// Lembre de colocar a o jar do driver de conecxao dentro da pasta do projeto
// ou importar em projeto/propriedades/classpatch/library

public class ConnectionFactory {
	
	// criar um metodo get connection que retorna uma conecxão
	// aclasse Connection é responsavel por fazer a transição entre a aplicação
	// e o banco de dados
	// Getconnection é o metodo que faz a instancia das conexões
	public Connection getConnection(){
		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/fj21?useSSL=false", "root", "xelaajr");// String de conexão
			
		} catch (SQLException e) {
			// vai ser responsavel por nos passar um feedback caso a conexao venha a falhar
			throw new DAOException(e);
		}
		
	}

}
