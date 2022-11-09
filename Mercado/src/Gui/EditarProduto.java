package Gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import Aplicacao.TelaEditar;
import Entidades.Produto;
import Gui.util.Alerta;
import JDBC.DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class EditarProduto implements Initializable{
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
@FXML
private TextField Id;
@FXML
private TextField Nome;
@FXML
private TextField Codigo;
@FXML
private TextField DataEntrada;
@FXML
private TextField Valor;
@FXML
private TextField departamentoID;
@FXML
private Button salvar;
@FXML
private Button cancelar;


private static Produto p2 ;

DAO dao = new DAO();

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	inicializarProduto();
	
}
public void inicializarProduto() {
	try {
	Id.setText(p2.getId().toString());
	Nome.setText(p2.getNomeProduto());
	Codigo.setText(String.valueOf(p2.getCodigoBarras()));
	DataEntrada.setText(sdf.format(p2.getDataEntrada()));
	Valor.setText(String.valueOf(p2.getValor()));
	departamentoID.setText(String.valueOf(p2.getDepartamentoId()));
	}
	catch(NullPointerException e) {
		e.getMessage();
	}
}
public static Produto getP2() {
	return p2;
}
public static void setP2(Produto p2) {
	EditarProduto.p2 = p2;
}
public void onBtSalvar()  {
	Produto pNovo;
	try {
		java.util.Date dt = sdf.parse(DataEntrada.getText());
		java.sql.Date d = new java.sql.Date(dt.getTime());
		pNovo = dao.buscar(p2.getId());
		pNovo.setNomeProduto(Nome.getText());
		pNovo.setCodigoBarras(Integer.parseInt(Codigo.getText()));
		pNovo.setDataEntrada(d);
		pNovo.setValor(Double.parseDouble(Valor.getText()));
		pNovo.setDepartamentoId(Integer.parseInt(departamentoID.getText()));
		dao.atualizarCodigo(pNovo);
		dao.atualizarNome(pNovo);
		dao.atualizarData(pNovo);
		dao.atualizarValor(pNovo);
		dao.atualizarDepartamento(pNovo);
		/*ja testei setando o id*/
		/*
		pNovo.setNomeProduto(Nome.getText());
		pNovo.setCodigoBarras(Integer.parseInt(Codigo.getText()));
		pNovo.setDataEntrada(d);
		pNovo.setValor(Double.parseDouble(Valor.getText()));
		pNovo.setDepartamentoId(Integer.parseInt(departamentoID.getText()));
		dao.atualizar(pNovo);
		*/
		Alerta.showAlert("Alteraçao", null, "Produto alterado com sucesso", AlertType.INFORMATION);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void onBtCancelar() {
	
	TelaEditar edit = new TelaEditar(p2);
	edit.getStage().close();
	
}

}
