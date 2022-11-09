package dao;

import Db.DB;
import dao.impl.DepartamentoDaoJDBC;
import dao.impl.ProdutoDaoJDBC;



public class DaoFactory {
	/* classe que serve para instanciar os DAOS*/
	
	public static produtoDAO createProdutoDao() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}
	public static DepartamentoDAO createDepartmentDao() {
		return new DepartamentoDaoJDBC(DB.getConnection());
	}
}

	

