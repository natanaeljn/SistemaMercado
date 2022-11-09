package Aplicacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdicionarProduto extends Application {
	private static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Gui/adicionarProduto.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
		setStage(stage);
		
	}
	public static Stage getStage() {
		return stage;
	}
	public static void  setStage(Stage stage) {
		AdicionarProduto.stage = stage;
	}

}
