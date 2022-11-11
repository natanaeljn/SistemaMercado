package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import Aplicacao.TelaPrincipal;
import Entidades.Login;
import Gui.util.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	String usuario = "admin";
	String senha = "admin";

	Login login = new Login(usuario, senha);

	@FXML
	private TextField user;
	@FXML
	private TextField tfSenha;
	@FXML
	private PasswordField psSenha;
	@FXML
	private Button entrar;
	@FXML
	private Button mostrarSenha;

	public void entre() {
		String us = user.getText();
		String se = psSenha.getText();
		login.correto(us, se);

		if (login.logs == true) {

			System.out.println("acertou");
			TelaPrincipal principal = new TelaPrincipal();
			try {
				principal.start(new Stage());
				fecharTela();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("errou");
			Alerta.showAlert("login e senha incorretos", null, "senha ou login errado", AlertType.WARNING);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* metodo para entrar com a tecla Enter */
		entrar.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				entre();
			}
		});

	}

	public void fecharTela() {
		Aplicacao.Login.getStage().close();
	}
	

	public void onBtMostrarSenha() {
		psSenha.setVisible(true);
			
	
}
}