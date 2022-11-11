package JDBC;

import java.util.List;

import Entidades.Produto;


public class teste {

	public  static void inicializarLista() {
		DAO dao = new DAO();
		Produto produto = new Produto();
		try {
			List<Produto>list = dao.Listar();
			for (Produto x : list) {
				System.out.println(x);
				System.out.println("----------------------------------------------");
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
}