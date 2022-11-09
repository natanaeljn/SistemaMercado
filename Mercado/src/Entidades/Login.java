package Entidades;

public class Login {
	
	private String usuario ; 
	private String senha ;
	public Boolean logs = false;
	public Login(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
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
	public void correto(String log , String senh) {
		
		if(log.equals(usuario) && senh.equals(senha)) {
			logs =true;
		}
		else {
			logs = false;
		}
	}
	

}
