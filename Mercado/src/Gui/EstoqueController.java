package Gui;


import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Aplicacao.TelaAdicionar;
import Aplicacao.TelaEditar;
import Aplicacao.TelaEstoque;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class EstoqueController implements Initializable {
	DAO dao = new DAO();
	@FXML
	private Button atualizar;
	
	@FXML
	private Button adicionarProduto;
	@FXML
	private Button removerProduto;
	@FXML
	private TableView<Produto> tableViewProduto;
	@FXML
	private Button editar;
	
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
	private TableColumn<Produto, Integer>tableColumnIdDepart;

	@FXML
	private TableColumn<Produto, Produto> tableColumnRemover;
	
	
	public static  ObservableList<Produto>obsList = FXCollections.observableArrayList();
	private Produto selecionado;
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
	}
	/*setar os nomes de acordo com os atributos da classe*/
	public void initializeNodes() {
		updateTableView();
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
		
		/*metodo para selecionar um item da tabela*/
		tableViewProduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				selecionado =  ( Produto) newValue;
			    
				
			}
			
		});
		
		
	}
	public static List<Produto> list = new ArrayList<Produto>();
	
	public void updateTableView() {
		
		
		
		try {
			list.addAll(dao.Listar());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obsList = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsList);
		
	}
	/*metodo para editar cada linha da coluna 
	private void botaoEditar() {
		
	    tableColumnId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	    tableColumnId.setOnEditCommit(e->{
	    	e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
	    });
	    tableColumnNome.setCellFactory(TextFieldTableCell.forTableColumn());
	    tableColumnNome.setOnEditCommit(e->{
	    	e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomeProduto(e.getNewValue());
	    });
	    tableColumnCodigo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	    tableColumnCodigo.setOnEditCommit(e->{
	    	e.getTableView().getItems().get(e.getTablePosition().getRow()).setCodigoBarras(e.getNewValue());
	    });
	    tableColumnDataEntrada.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
	    tableColumnDataEntrada.setOnEditCommit(e->{
	    	e.getTableView().getItems().get(e.getTablePosition().getRow()).setDataEntrada(e.getNewValue());
	    });
	    tableColumnValor.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
	    tableColumnValor.setOnEditCommit(e->{
	    	e.getTableView().getItems().get(e.getTablePosition().getRow()).setValor(e.getNewValue());
	    });
	    tableColumnIdDepart.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	    tableColumnIdDepart.setOnEditCommit(e->{
	    	e.getTableView().getItems().get(e.getTablePosition().getRow()).setDepartamentoId(e.getNewValue());
	    });
	    tableViewProduto.setEditable(true);
	    
}	
*/
	public void onBtEditar() {
		
		alteraProduto(selecionado);
	    
}	
	/*metodo para carregar o objeto produto para a outra classe*/
	public void  alteraProduto(Produto p1) {
		if(p1!= null) {
			TelaEditar tela = new  TelaEditar(p1);
			zeraListaObs();
		    try {
				tela.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}}
	public void onBtAtualizar() {
		initializeNodes();
	}
	public static void zeraListaObs() {
	
		list.clear();
		
	}
	public void onBtAdicionar() {
		TelaAdicionar adicionar = new TelaAdicionar();
		zeraListaObs();
		try {
			adicionar.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onBtRemover() {
		Integer idDelet = selecionado.getId();
		dao.deletar(idDelet);
		zeraListaObs();
		Alerta.showAlert("Removido", null, "Produto removido com sucesso", AlertType.INFORMATION);
	}
}