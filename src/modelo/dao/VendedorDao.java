package modelo.dao;

import java.util.List;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public interface VendedorDao {

	public void insert(Vendedor dep);
	public void update(Vendedor dep);
	public void deleteById(Integer id);
	public Vendedor findById(Integer id);
	public List<Vendedor> findAll();
	public List<Vendedor> findByDepartamento(Departamento departamento);

}
