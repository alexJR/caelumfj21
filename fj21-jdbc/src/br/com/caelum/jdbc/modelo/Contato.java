package br.com.caelum.jdbc.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//Crie a classe de Contato no pacote br.com.caelum.jdbc.modelo. 
// Ela será nosso JavaBean para representar a entidade do banco.
// Deverá ter id, nome, email, endereço e uma datade nascimento
public class Contato {

	private Long id;
	private String nome;
	private String email;
	private String endereco;
	private Calendar data_nascimento;
	
	// Criar os metodos Getters e Setters (Métodos Especiais)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Calendar getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Calendar data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormata = sdf.format(this.data_nascimento.getTime());
		
		return "Contato [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco
				+ ", data_nascimento=" + dataFormata + "]";
	} 
	
	
	
}
