package Gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import Db.DAO;
import Entidades.Produto;
import Gui.util.Alerta;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class CaixaController implements Initializable {
	DAO dao = new DAO();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
	@FXML
	private TextField quantidadeTx;

	public static ObservableList<Produto> obsList = FXCollections.observableArrayList();
	public static Set<Produto> list = new HashSet<Produto>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
	}
	

	public void initializeNodes() {
		
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
		tableColumnDataEntrada.setCellValueFactory(new PropertyValueFactory<Produto,Date>(("dataEntrada")));
		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tableColumnIdDepart.setCellValueFactory(new PropertyValueFactory<>("departamentoId"));
        tableViewProduto.setItems(obsList);
		tableViewProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	};

	public void updateTableView(Integer  prod) {

		try {
			list.add(dao.buscar(prod));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		obsList = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsList);

	}

	public static void zeraListaObs() {

		list.clear();

	}

	public void onBtAdicionar()  {
		Integer busca = Integer.parseInt(buscar.getText());
		
		try {
			dao.buscar(busca);
			dao.buscarCodigo(busca);
           
			
             if (dao.buscar(busca) != null) {
				
				updateTableView(busca);
				initializeNodes();
				Integer quantidade = Integer.parseInt(quantidadeTx.getText());
				onLabel(quantidade);
			
			}
             else if(dao.buscarCodigo(busca) != null){
            	 updateTableView(busca);
 				initializeNodes();
 				Integer quantidade = Integer.parseInt(quantidadeTx.getText());
				onLabel(quantidade);
             }
			
			
		}
		catch(Exception e) {
			Alerta.showAlert("nulo", "Valor do id nao existe , digite novamente", null, AlertType.ERROR);
		}
		
		}
		
		
		
	
	public Double total ( ) {
		double tot = 0 ;
		for(Produto x : list) {
			tot+= x.getValor();
		}
		return tot;
	}
	public void onLabel(Integer quantidade) {
		result.setText(String.valueOf(total() * quantidade));
		
	}

}
