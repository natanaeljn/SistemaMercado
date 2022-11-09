package dao;

import java.util.List;

import Entidades.Departamento;

public interface DepartamentoDAO {
	void insert(Departamento obj);

	void update(Departamento obj);

	void deleteById(Integer id);

	Departamento findById(Integer id);

	List<Departamento> findAll();
	 

}
