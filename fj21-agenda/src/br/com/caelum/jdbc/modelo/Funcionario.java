package br.com.caelum.jdbc.modelo;
// Crie uma classe chamada Funcionario com os campos id (Long), nome, usuario e senha (String).
public class Funcionario {
	
	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	
	
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", usuario=" + usuario + ", senha=" + senha + "]";
	}
	
	

	
}
