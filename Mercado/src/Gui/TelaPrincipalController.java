package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import Aplicacao.Login;
import Aplicacao.TelaCaixa;
import Aplicacao.TelaCliente;
import Aplicacao.TelaEstoque;
import Aplicacao.TelaPrincipal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaPrincipalController implements Initializable {

	@FXML
	private Button caixa;
	@FXML
	private Button clientes;
	@FXML
	private Button estoque;
	@FXML
	private Button sair;

	public void onBtcaixa() {
         TelaCaixa caixa = new TelaCaixa();
         try {
			caixa.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onBtclientes() {
		TelaCliente cliente = new TelaCliente();
		try {
			cliente.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onBtestoque() {
		TelaEstoque estoque = new TelaEstoque();
		try {
			estoque.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onBtsair() {
		fechar();

	}

	/*
	 * private synchronized<T> void loadView(String
	 * absoluteName,Consumer<T>acaoInicial) { try { FXMLLoader loader = new
	 * FXMLLoader(getClass().getResource(absoluteName)); VBox newVBox =
	 * loader.load(); Scene mainScene =Main.getMainScene(); VBox mainVBox = (VBox)
	 * ((ScrollPane) mainScene.getRoot()).getContent();
	 * 
	 * Node mainMenu = mainVBox.getChildren().get(0);
	 * mainVBox.getChildren().clear(); mainVBox.getChildren().add(mainMenu);
	 * mainVBox.getChildren().addAll(newVBox.getChildren()); T controller =
	 * loader.getController(); acaoInicial.accept(controller);
	 * 
	 * 
	 * } catch (IOException e) { Alerta.showAlert("Excessao IO",
	 * "erro de carregamento da pagina", e.getMessage(), AlertType.ERROR); } }
	 */
	public void fechar() {
		TelaPrincipal.getStage().close();
		Login login = new Login();
		try {
			login.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	
	
}
