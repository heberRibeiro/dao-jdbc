package modelo.dao;

import db.DB;
import modelo.dao.impl.VendedorDaoJDBC;

public class FabricaDao {

	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJDBC(DB.getConnection());
	}

}
