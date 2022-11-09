package dao;

import java.util.List;

import Entidades.Departamento;
import Entidades.Produto;

public interface produtoDAO {
	void insert(Produto obj);

	void update(Produto obj);

	void deleteById(Integer id);

	Produto findById(Integer id);

	List<Produto> findAll();

	List<Produto> findByDepartment(Departamento departamento);

}
