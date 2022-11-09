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
import javafx.scene.control.TextField;
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
	private TextField sen;
	@FXML
	private Button entrar;

	public void entre() {
		String us = user.getText();
		String se = sen.getText();
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

}
