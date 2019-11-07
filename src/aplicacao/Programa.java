package aplicacao;

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


	}

}
