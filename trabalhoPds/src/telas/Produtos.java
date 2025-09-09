package telas;

public class Produtos {
	
	private String nome;
	private String preco;
	
	
	public Produtos(String nome, String preco) {
		
		this.nome = nome;
		this.preco = preco;
		
	}
	
	public Produtos() {}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public void setPreco(String preco) {
		this.preco = preco;
	}



	public String getNome() {
		return nome;
	}

	

	public String getPreco() {
		return preco;
	}

	

}
