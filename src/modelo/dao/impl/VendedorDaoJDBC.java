package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn = null;

	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor vend) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, vend.getNome());
			ps.setString(2, vend.getEmail());
			ps.setDate(3, new java.sql.Date(vend.getDataAniversario().getTime()));
			ps.setDouble(4, vend.getSalarioBase());
			ps.setInt(5, vend.getDepartamento().getId());

			int nLinhasAlteradas = ps.executeUpdate();

			if (nLinhasAlteradas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					vend.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado. Nenhuma alteração realizada!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
		}

	}

	@Override
	public void update(Vendedor vend) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " 
					+ "WHERE Id = ?");

			ps.setString(1, vend.getNome());
			ps.setString(2, vend.getEmail());
			ps.setDate(3, new java.sql.Date(vend.getDataAniversario().getTime()));
			ps.setDouble(4, vend.getSalarioBase());
			ps.setInt(5, vend.getDepartamento().getId());
			ps.setInt(6, vend.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
		}

	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? ");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento dep = instanciarDepartamento(rs);
				Vendedor vend = instanciarVendedor(rs, dep);
				return vend;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}

	}

	private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vend = new Vendedor();
		vend.setId(rs.getInt("Id"));
		vend.setNome(rs.getString("Name"));
		vend.setEmail(rs.getString("Email"));
		vend.setSalarioBase(rs.getDouble("BaseSalary"));
		vend.setDataAniversario(rs.getDate("BirthDate"));
		vend.setDepartamento(dep);
		return vend;
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name");

			rs = ps.executeQuery();

			List<Vendedor> listaVendedores = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			while (rs.next()) {

				Departamento dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Vendedor vend = instanciarVendedor(rs, dep);
				listaVendedores.add(vend);
			}
			return listaVendedores;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");

			ps.setInt(1, departamento.getId());
			rs = ps.executeQuery();

			List<Vendedor> listaVendedores = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			while (rs.next()) {

				Departamento dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Vendedor vend = instanciarVendedor(rs, dep);
				listaVendedores.add(vend);
			}
			return listaVendedores;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
			DB.closeResultSet(rs);
		}
	}

}
