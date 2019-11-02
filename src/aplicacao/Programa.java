package aplicacao;

import modelo.dao.FabricaDao;
import modelo.dao.VendedorDao;
import modelo.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
				
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		System.out.println("=== TESTE 1: Vendedor findById ===");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);

	}

}
