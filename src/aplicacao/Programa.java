package aplicacao;

import java.util.Date;
import java.util.List;

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

		System.out.println("\n=== TESTE 4: Vendedor insert ===");
		Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
		vendedorDao.insert(novoVendedor);
		System.out.println("Inserido! Id: " + novoVendedor.getId());
		
		System.out.println("\n=== TESTE 5: Vendedor update ===");
		vendedor = vendedorDao.findById(1);
		vendedor.setNome("Martha Waine");
		vendedorDao.update(vendedor);
		System.out.println("Update realizado!");
		
		System.out.println("\n=== TESTE 6: Vendedor delete ===");
		int id = 9;
		vendedorDao.deleteById(id);
		System.out.println("Delete realizado!");

	}

}
