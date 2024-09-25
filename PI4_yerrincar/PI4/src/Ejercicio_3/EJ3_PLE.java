package Ejercicio_3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import Datos.DatosDistribuidor;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class EJ3_PLE {
	public static List<Integer> destinos;
	public static List<Integer> productos;
	public static List<List<Integer>> precios;

	public static List<Integer> getDestinos() {
		return destinos;
	}

	public static List<Integer> getProductos() {
		return productos;
	}

	public static List<List<Integer>> getPrecios() {
		return precios;
	}

	public static Integer getNumDestinos() {
		return destinos.size();
	}

	public static Integer getNumProductos() {
		return productos.size();
	}

	public static Integer getPrecio(Integer i, Integer j) {
		return precios.get(j).get(i);
	}

	public static Integer getCantidadProductos(Integer j) {
		return productos.get(j);
	}
	public static Integer getDestinoI(Integer i) {
		return destinos.get(i);
	}

	public static void model_ejercicio3() throws IOException {

		for (int k = 1; k <= 3; k++) {
			DatosDistribuidor.iniDatos("Ficheros/Ejercicio3DatosEntrada" + k + ".txt");
			
			destinos = DatosDistribuidor.destinos;
			productos = DatosDistribuidor.productos;
			precios = DatosDistribuidor.precios;
			
			AuxGrammar.generate(EJ3_PLE.class, "lsi_models/Ejercicio3.lsi", "gurobi_models/Ejercicio3_" + k + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio3_" + k + ".lp");
			Locale.setDefault(Locale.of("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}

	}

	public static void main(String[] args) throws IOException {
		model_ejercicio3();
	}

}
