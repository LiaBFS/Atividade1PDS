package model;

public class Usuarios {
	
	private String user;
	private String cpf;
	
	public Usuarios(String user, String cpf) {
		
		this.user = user;
		this.cpf = cpf;
	}
	
	public Usuarios() {}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
