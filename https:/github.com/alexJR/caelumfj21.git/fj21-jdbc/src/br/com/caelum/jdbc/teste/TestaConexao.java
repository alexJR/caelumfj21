package br.com.caelum.jdbc.teste;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.dao.FuncionarioDAO;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaConexao {
	public static void main(String[] args) throws SQLException {

		//ADICIONA O CONTATO
		/*
		Contato c1 = new Contato();

		
		c1.setNome("alan");
		c1.setEmail("allan_rampa@gmail.com");
		c1.setEndereco("Av. Giacomo Lunardi");
		c1.setData_nascimento(Calendar.getInstance());
		
		
		ContatoDao cdao = new ContatoDao();
		
		cdao.adiciona(c1);
		
		System.out.println("Contato Adicionado");
		*/

		//ALTERAR UM CONTATO
		/*
		ContatoDao cdao = new ContatoDao();
		Contato cont = cdao.pesquisa(3);
		
		// Somente alterar o nome e o email
		cont.setNome("Allan Rampanelli");
		cont.setEmail("allan_rampanelli@hotmail.com");

		
		cdao.altera(cont);
		*/
		
		Contato c4 = new Contato();
		c4.setNome("Junior");
		c4.setEmail("junior@hotmail.com");
		c4.setEndereco("Avenida Paulista");
		c4.setData_nascimento(Calendar.getInstance());
		
		ContatoDAO c4dao = new ContatoDAO();
		c4dao.adiciona(c4);
		
		// REMOVE CONTATO
		/*
		ContatoDao cdao = new ContatoDao();
		Contato contatoExcluir = cdao.pesquisa(5);
		
		cdao.remover(contatoExcluir);
		*/
		
		
		//PESQUISA TODOS OS CONTATOS
		/*
		// Criar uma instancia de contatoDAO
		ContatoDao cdao = new ContatoDao();
		//Adicionar os contatos em uma lista
		List<Contato> contatos = cdao.getLista();
		
		// Mostrar a Lista com um foreach
		for (Contato contato: contatos) {
			System.out.println(contato.toString());
		}
		*/
		
		//BUSCA UM CONTATO POR ID
		/*
		ContatoDao cdao = new ContatoDao();
		Contato c1 = cdao.pesquisa(2);
		
		System.out.println(c1.toString());
		*/
		
		// ======== FUNCIONARIOS ========
		
		// ADICIONA
		/*
		Funcionario f1 = new Funcionario();
		
		f1.setNome("Ricardo");
		f1.setUsuario("Ricss");
		f1.setSenha("0011rr");
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		fdao.adiciona(f1);
		*/
		
		//PESQUISA
		/*
		FuncionarioDAO cdao = new FuncionarioDAO();
		
		Funcionario funcionario = cdao.pesquisa(1);
		
		System.out.println(funcionario.toString());
		*/
		
		//REMOVE
		/*
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		Funcionario funcionario = fdao.pesquisa(5);
		
		fdao.remover(funcionario);
		*/
		
		//PESQUISA TODOS FUNCIONARIOS
		
		/*
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		List<Funcionario> funcionarios = fdao.getLista();
		
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.toString());
		}
		
		*/
		
	}
	
	

}
