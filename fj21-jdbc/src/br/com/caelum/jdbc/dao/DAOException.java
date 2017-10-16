package br.com.caelum.jdbc.dao;

// 2) Crie uma classe chamada DAOException que estenda de RuntimeException e utilize-a no seu ContatoDAO.
public class DAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(){
		// vaziu
	}
	
	public DAOException(String mensagem){
		super(mensagem);
		
	}
	
	public DAOException(Throwable causa){
		super(causa);
	}
	
}
