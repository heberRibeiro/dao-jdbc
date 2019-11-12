package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import modelo.dao.DepartamentoDao;
import modelo.entidades.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {

	private Connection conn;

	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Departamento dep) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO department " + "(Name) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dep.getNome());

			int nLinhasAlteradas = ps.executeUpdate();

			if (nLinhasAlteradas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					dep.setId(id);
				}
				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
		}
	}

	@Override
	public void update(Departamento dep) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE department " + "SET Name = ? " + "WHERE Id = ?");

			ps.setString(1, dep.getNome());
			ps.setInt(2, dep.getId());

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
			ps = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
		}
	}

	@Override
	public Departamento findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT department.* " + "FROM department " + "WHERE Id = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Departamento dep = instanciarDepartamento(rs);
				return dep;
			} else {
				throw new DbException("Erro inesperado. Consulta não concluída");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
			DB.closeResultSet(rs);
		}
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("Id"));
		dep.setNome(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Departamento> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM department " + "ORDER BY Id");
			rs = ps.executeQuery();
			List<Departamento> departamentos = new ArrayList<>();
			while (rs.next()) {
				Departamento dep = instanciarDepartamento(rs);
				departamentos.add(dep);
			}
			return departamentos;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(ps);
			DB.closeResultSet(rs);
		}
	}

}
