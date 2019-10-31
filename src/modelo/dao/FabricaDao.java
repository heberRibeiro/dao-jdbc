package modelo.dao;

import modelo.dao.impl.VendedorDaoJDBC;

public class FabricaDao {

	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJDBC();
	}

}
