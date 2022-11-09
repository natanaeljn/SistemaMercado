package Entidades;

import java.util.Date;
import java.util.Objects;

import javafx.scene.control.Button;

public class Produto {

	private Integer id ;
	private  String nomeProduto;
	private Integer codigoBarras;
	private Date dataEntrada;
	private double valor ;
	Departamento departamento ; 
	private Integer departamentoId;
	 Button update;
	
	
	
	public Produto() {
		
	}
	
	
	public Produto(Integer id, String nomeProduto, Integer codigoBarras, Date dataEntrada, double valor,
			Integer departamentoId,Button update) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.codigoBarras = codigoBarras;
		this.dataEntrada = dataEntrada;
		this.valor = valor;
		this.departamentoId = departamentoId;
		this.update = new Button("atualizar");
	}


	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Integer getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(Integer codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Button getUpdate() {
		return update;
	}


	public void setUpdate(Button update) {
		this.update = update;
	}


	public java.sql.Date getDataEntrada() {
		return (java.sql.Date) dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
	public Integer getDepartamentoId() {
		return departamentoId;
	}


	public void setDepartamentoId(Integer departamentoId) {
		this.departamentoId = departamentoId;
	}


	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoBarras, dataEntrada, id, nomeProduto, valor);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(codigoBarras, other.codigoBarras) && Objects.equals(dataEntrada, other.dataEntrada)
				&& Objects.equals(id, other.id) && Objects.equals(nomeProduto, other.nomeProduto)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}


	@Override
	public String toString() {
		return "Produto [id=" + id + ", nomeProduto=" + nomeProduto + ", codigoBarras=" + codigoBarras
				+ ", dataEntrada=" + dataEntrada + ", valor=" + valor + ", departamentoId=" + departamentoId + "]";
	}
	
	
}
