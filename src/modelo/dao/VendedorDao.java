package modelo.dao;

import java.util.List;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public interface VendedorDao {

	public void insert(Vendedor vend);
	public void update(Vendedor vend);
	public void deleteById(Integer id);
	public Vendedor findById(Integer id);
	public List<Vendedor> findAll();
	public List<Vendedor> findByDepartamento(Departamento departamento);

}
