package aplicacao;

import java.util.List;

import modelo.dao.DepartamentoDao;
import modelo.dao.FabricaDao;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {

		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();

		System.out.println("=== TESTE 1: Vendedor findById ===");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);

		System.out.println("\n=== TESTE 2: Vendedor findByDepartamento ===");
		Departamento dep = new Departamento(2, null);
		List<Vendedor> listaVendedores = vendedorDao.findByDepartamento(dep);
		for (Vendedor vend : listaVendedores) {
			System.out.println(vend);
		}

		System.out.println("\n=== TESTE 3: Vendedor findAll ===");
		listaVendedores = vendedorDao.findAll();
		for (Vendedor vend : listaVendedores) {
			System.out.println(vend);
		}

//		System.out.println("\n=== TESTE 4: Vendedor insert ===");
//		Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
//		vendedorDao.insert(novoVendedor);
//		System.out.println("Inserido! Id: " + novoVendedor.getId());
//		
//		System.out.println("\n=== TESTE 5: Vendedor update ===");
//		vendedor = vendedorDao.findById(1);
//		vendedor.setNome("Martha Waine");
//		vendedorDao.update(vendedor);
//		System.out.println("Update realizado!");
//		
//		System.out.println("\n=== TESTE 6: Vendedor delete ===");
//		int id = 9;
//		vendedorDao.deleteById(id);
//		System.out.println("Delete realizado!");

		DepartamentoDao departamentoDao = FabricaDao.criarDepartamentoDao();

		System.out.println("\n==== TESTE 7: Departamento findById ===");
		Departamento departamento = departamentoDao.findById(2);
		System.out.println(departamento);

		System.out.println("\n=== TESTE 8: Departamento findAll ===");
		List<Departamento> departamentoFindAll = departamentoDao.findAll();
		for (Departamento depart : departamentoFindAll) {
			System.out.println(depart);
		}

//		System.out.println("\n=== TESTE 9: Departamento insert ===");
//		Departamento novoDep = new Departamento(null, "D2");
//		departamentoDao.insert(novoDep);
//		System.out.println("Inserido! Id: " + novoDep.getId());

		System.out.println("\n=== TESTE 10: Departamento update ===");
		Departamento novoDep = new Departamento(23, "Dep23");
		departamentoDao.update(novoDep);
		System.out.println("Atualizado! Id: " + novoDep.getId() + ", Nome: " + novoDep.getNome());
	}

}
