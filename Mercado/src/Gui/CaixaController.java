package Gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import Entidades.Produto;
import Gui.util.Alerta;
import Gui.util.Utils;
import JDBC.DAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class CaixaController implements Initializable {
	DAO dao = new DAO();
	@FXML
	private Button adicionar;
	@FXML
	private Button voltar;
	@FXML
	private TableView<Produto> tableViewProduto;

	@FXML
	private TableColumn<Produto, Integer> tableColumnId;
	@FXML
	private TableColumn<Produto, String> tableColumnNome;
	@FXML
	private TableColumn<Produto, Integer> tableColumnCodigo;
	@FXML
	private TableColumn<Produto, Date> tableColumnDataEntrada;
	@FXML
	private TableColumn<Produto, Double> tableColumnValor;
	@FXML
	private TableColumn<Produto, Integer> tableColumnIdDepart;
	@FXML
	private TextField buscar;
	@FXML
	private Label result;

	public static ObservableList<Produto> obsList = FXCollections.observableArrayList();
	public static Set<Produto> list = new HashSet<Produto>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
	}

	public void initializeNodes() {
		
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
		tableColumnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
		Utils.formatTableColumnDate(tableColumnDataEntrada, "dd/MM/yyyy");
		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		Utils.formatTableColumnDouble(tableColumnValor, 2);
		tableColumnIdDepart.setCellValueFactory(new PropertyValueFactory<>("departamentoId"));
        tableViewProduto.setItems(obsList);
		tableViewProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	};

	public void updateTableView(Integer  prod) {

		try {
			list.add(dao.buscar(prod));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obsList = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsList);

	}

	public static void zeraListaObs() {

		list.clear();

	}

	public void onBtAdicionar() {
		Integer busca = Integer.parseInt(buscar.getText());
		try {
			dao.buscar(busca);
			dao.buscarCodigo(busca);
			if (dao.buscar(busca) != null||dao.buscarCodigo(busca) != null) {
				updateTableView(busca);
				initializeNodes();
				onLabel();
			
			}
			else {
				Alerta.showAlert("Erro", null, "Produto nao encontrado , digite novamente", AlertType.WARNING);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Double total ( ) {
		double tot = 0 ;
		for(Produto x : list) {
			tot+= x.getValor();
		}
		return tot;
	}
	public void onLabel() {
		result.setText(String.valueOf(total()));
	}

}
