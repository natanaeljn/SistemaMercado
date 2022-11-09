package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Db.DB;
import Db.DbException;
import Entidades.Departamento;
import Entidades.Produto;
import dao.produtoDAO;



public class ProdutoDaoJDBC implements produtoDAO {

	private Connection conn ; 
	
	 public ProdutoDaoJDBC ( Connection conn) {
		 this.conn = conn ;
	 }
	 public ProdutoDaoJDBC() {
		 
	 }
	
	
	
	@Override
	public void insert(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO produto "
					+ "(Nome, codigo, DataEntrada, Valor, DepartamentoId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNomeProduto());
			st.setInt(2, obj.getCodigoBarras());
			st.setDate(3, new java.sql.Date(obj.getDataEntrada().getTime()));
			st.setDouble(4, obj.getValor());
			st.setInt(5, obj.getDepartamento().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE produto "
					+ "SET Nome = ?, codigo = ?, DataEntrada = ?, Valor = ?, DepartamentoId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getNomeProduto());
			st.setInt(2, obj.getCodigoBarras());
			st.setDate(3, new java.sql.Date(obj.getDataEntrada().getTime()));
			st.setDouble(4, obj.getValor());
			st.setInt(5, obj.getDepartamento().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM produto WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Produto findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			"SELECT produto.*,departamento.Name as DepName "
			+ "FROM produto INNER JOIN departamento "
			+ "ON produto.DepartamentoId = departamento.Id "
			+ "WHERE produto.Id = ? ") ;
			
			st.setInt(1, id);
			rs = st.executeQuery();
			/*O resultSet tras nossos dados em forma de tabela  , por isso precisamos instanciar isso em objetos , de acordo com a classe*/
			if(rs.next()) {
				Departamento dep = new Departamento();
				dep.setId(rs.getInt("DepartamentoId"));
				dep.setNomeDepartamento(rs.getString("DepName"));
				Produto obj = new Produto();
				obj.setId(rs.getInt("Id"));
				obj.setNomeProduto(rs.getString("Nome"));
				obj.setCodigoBarras(rs.getInt("codigo"));
				obj.setDataEntrada(rs.getDate("DataEntrada"));
				obj.setValor(rs.getDouble("Valor"));
				obj.setDepartamento(dep);
				return obj;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}
}
	private Departamento instantiateDepartment(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartamentoId"));
		dep.setNomeDepartamento(rs.getString("DepName"));
		return dep;
	}
	
	private Produto instantiateSeller(ResultSet rs, Departamento dep) throws SQLException {
		Produto obj = new Produto();
		obj.setId(rs.getInt("Id"));
		obj.setNomeProduto(rs.getString("Nome"));
		obj.setCodigoBarras(rs.getInt("codigo"));
		obj.setDataEntrada(new java.util.Date(rs.getTimestamp("DataEntrada").getTime()));
		obj.setValor(rs.getDouble("Valor"));
		obj.setDepartamento(dep);
		return obj;
	}
	
	

	@Override
	public List<Produto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT produto.*,departamento.Name as DepName "
					+ "FROM produto INNER JOIN departamento "
					+ "ON produto.DepartamentoId = departamento.Id "
					+ "ORDER BY Nome");
			
			rs = st.executeQuery();
			
			List<Produto> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartamentoId"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Produto obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	@Override
	public List<Produto> findByDepartment(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT produto.*,departamento.Name as DepName "
					+ "FROM produto INNER JOIN departamento "
					+ "ON produto.DepartamentoId = departamento.Id "
					+ "WHERE DepartamentoId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, departamento.getId());
			
			rs = st.executeQuery();
			
			List<Produto> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartamentoId"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartamentoId"), dep);
				}
				
				Produto obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}



