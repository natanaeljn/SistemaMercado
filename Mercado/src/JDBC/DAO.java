package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Departamento;
import Entidades.Produto;





public class DAO {
	private Connection conn ;
	public DAO() {
		conn = ConnectionFactory.getConnection();
	}
	public void salvar(Produto produto) {
		
		try {
			String sql = "insert  into  produto  ( Nome, codigo,DataEntrada,Valor,DepartamentoId)  values (?,?,?,?,?)";
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, produto.getNomeProduto());
			insert.setInt(2, produto.getCodigoBarras());
			insert.setDate(3, produto.getDataEntrada());
			insert.setDouble(4, produto.getValor());
			insert.setInt(5, produto.getDepartamentoId());
			insert.execute();
			conn.commit();
			
			
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
		public List<Produto>Listar() throws Exception {
			List<Produto> list = new ArrayList<Produto>();
			/* aqui estamos buscando tudo na tabela sql */
			String sql = "select*from produto";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			/* buscamos pelo nome da coluna , exemplo id */
			while (resultado.next()) {
				Produto produto = new Produto();
				produto.setId(resultado.getInt("Id"));
				produto.setNomeProduto(resultado.getString("Nome"));
				produto.setCodigoBarras(resultado.getInt("codigo"));
				produto.setDataEntrada(resultado.getDate("DataEntrada"));
				produto.setValor(resultado.getDouble("Valor"));
				produto.setDepartamentoId(resultado.getInt("DepartamentoId"));
				list.add(produto);
			}

			return list;

		}
		public List<Departamento>ListarDep() throws Exception {
			List<Departamento> list = new ArrayList<Departamento>();
			/* aqui estamos buscando tudo na tabela sql */
			String sql = "select*from departamento";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			/* buscamos pelo nome da coluna , exemplo id */
			while (resultado.next()) {
				Departamento departamento = new Departamento();
				departamento.setId(resultado.getInt("Id"));
				departamento.setNomeDepartamento(resultado.getString("Name"));
				
				list.add(departamento);
			}

			return list;

		}
		/*
		public void atualizar(Produto produto) {
			try {
				String sql = "update produto set Nome = ? ,set codigo = ? ,set DataEntrada = ? ,set Valor = ? ,set DepartamentoId = ? where id = " + produto.getId();
				PreparedStatement statement = conn.prepareStatement(sql);
				
				statement.setString(1, produto.getNomeProduto());
				statement.setInt(2, produto.getCodigoBarras());
				statement.setDate(3, produto.getDataEntrada());
				statement.setDouble(4, produto.getValor());
				statement.setInt(5, produto.getDepartamentoId());
				statement.execute();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
		  
		
		
		
	}
	*/
		public void atualizarNome(Produto produto) {
			try {
				String sql = "update produto set Nome = ? where id = " + produto.getId();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, produto.getNomeProduto());
				statement.execute();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			}
		public void atualizarCodigo(Produto produto) {
			try {
				String sql = "update produto set codigo = ? where id = " + produto.getId();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, produto.getCodigoBarras());
				statement.execute();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			}
		public void atualizarData(Produto produto) {
			try {
				String sql = "update produto set DataEntrada = ? where id = " + produto.getId();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setDate(1, produto.getDataEntrada());
				statement.execute();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			}
		public void atualizarValor(Produto produto) {
			try {
				String sql = "update produto set Valor = ? where id = " + produto.getId();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setDouble(1, produto.getValor());
				statement.execute();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			}
		public void atualizarDepartamento(Produto produto) {
			try {
				String sql = "update produto set DepartamentoId = ? where id = " + produto.getId();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, produto.getDepartamentoId());
				statement.execute();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			}
		public Produto buscar(Integer id) throws Exception {
			Produto retorno = new Produto();
			/* aqui estamos buscando tudo na tabela sql */
			String sql = "select*from produto where id =" + id;
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			/* buscamos pelo nome da coluna , exemplo id */
			while (resultado.next()) {

				retorno.setId(resultado.getInt("Id"));
				retorno.setNomeProduto(resultado.getString("Nome"));
				retorno.setCodigoBarras(resultado.getInt("codigo"));
				retorno.setDataEntrada(resultado.getDate("DataEntrada"));
				retorno.setValor(resultado.getDouble("Valor"));
				retorno.setId(resultado.getInt("DepartamentoId"));

			}

			return retorno;

		}
		public void deletar( Integer id) {
			try {
				String sql = "delete from produto  where id   = " + id;
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.execute();
				
				conn.commit();
			}
			catch(Exception e ) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
		}
		public Produto buscarCodigo(Integer codigo) throws Exception {
			Produto retorno = new Produto();
			
			String sql = "select*from produto where codigo =" + codigo;
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			/* buscamos pelo nome da coluna , exemplo id */
			while (resultado.next()) {

				retorno.setId(resultado.getInt("Id"));
				retorno.setNomeProduto(resultado.getString("Nome"));
				retorno.setCodigoBarras(resultado.getInt("codigo"));
				retorno.setDataEntrada(resultado.getDate("DataEntrada"));
				retorno.setValor(resultado.getDouble("Valor"));
				retorno.setId(resultado.getInt("DepartamentoId"));

			}

			return retorno;

		}

}

