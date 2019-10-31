package aplicacao;

import java.util.Date;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {

		Departamento dep = new Departamento(1, "Departament de Livros");
		Vendedor vend = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, dep);

		System.out.println(dep);
		System.out.println(vend);

	}

}
