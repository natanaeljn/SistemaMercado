package Gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import Aplicacao.TelaAdicionar;
import Entidades.Produto;
import Gui.util.Alerta;
import JDBC.DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class AdicionarController implements Initializable{
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
@FXML
private TextField IdProduto;
@FXML
private TextField NomeProduto;
@FXML
private TextField CodigoProduto;
@FXML
private TextField DataEntradaProduto;
@FXML
private TextField ValorProduto;
@FXML
private TextField departamentoIDProduto;
@FXML
private Button salvarProduto;
@FXML
private Button cancelarProduto;




DAO dao = new DAO();

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	
}



public void onBtSalvarProduto() {
	Produto prod = new Produto();
	try {
		java.util.Date dt = sdf.parse(DataEntradaProduto.getText());
		java.sql.Date d = new java.sql.Date(dt.getTime());
		prod.setNomeProduto(NomeProduto.getText());
		prod.setCodigoBarras(Integer.parseInt(CodigoProduto.getText()));
		prod.setDataEntrada(d);
		prod.setValor(Double.parseDouble(ValorProduto.getText()));
		prod.setDepartamentoId(Integer.parseInt(departamentoIDProduto.getText()));
		dao.salvar(prod);
		Alerta.showAlert("Adicionado", null, "Produto adicionado com sucesso", AlertType.INFORMATION);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void onBtCancelarProduto() {
	
	TelaAdicionar add = new TelaAdicionar();
	add.getStage().close();
	
}

}
