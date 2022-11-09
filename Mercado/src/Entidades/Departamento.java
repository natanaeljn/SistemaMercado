package Entidades;

import java.util.Objects;

public class Departamento {
	private Integer id ;
	private String nomeDepartamento;
	
	public Departamento() {
		
	}
	
	public Departamento(Integer id, String nomeDepartamento) {
		super();
		this.id = id;
		this.nomeDepartamento = nomeDepartamento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeDepartamento() {
		return nomeDepartamento;
	}
	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nomeDepartamento);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeDepartamento, other.nomeDepartamento);
	}
	
	

}
