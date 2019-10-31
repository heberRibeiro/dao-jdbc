package modelo.dao;

import java.util.List;

import modelo.entidades.Departamento;

public interface DepartamentoDao {
	
	public void insert(Departamento dep);
	public void update(Departamento dep);
	public void deleteById(Integer id);
	public Departamento findById(Integer id);
	public List<Departamento> findAll();

}
