package model;

public class Usuarios {
	
	private String user;
	private String cpf;
	private boolean admin;
	
	public Usuarios(String user, String cpf, boolean admin) {
		
		this.user = user;
		this.cpf = cpf;
		this.admin = admin;
	}
	
	public Usuarios() {}
	
	public Usuarios (String user, String cpf) {
		this.user = user;
		this.cpf = cpf;
	}

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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	

}
