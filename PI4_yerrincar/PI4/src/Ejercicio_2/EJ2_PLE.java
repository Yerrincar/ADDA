package Ejercicio_2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import Datos.DatosProductos;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class EJ2_PLE {
	public static Integer presupuesto;
	public static List<Integer> preciosProductos;
	public static List<Integer> categoriasProductos;
	public static List<Integer> valoraciones;

	public static Integer getPresupuesto() {
		return presupuesto;
	}

	public static Integer getNumProductos() {
		return categoriasProductos.size();
	}

	public static List<Integer> getCategorias() {
		return categoriasProductos;
	}

	public static Integer getNumCategorias() {
		return categoriasProductos.stream().distinct().collect(Collectors.toSet()).size();
	}

	public static List<Integer> getPrecios() {
		return preciosProductos;
	}

	public static Integer getPrecioProducto(Integer i) {
		return preciosProductos.get(i);
	}

	public static Integer getCategoriaProducto(Integer i) {
		return categoriasProductos.get(i);
	}

	public static Integer getValoracionProducto(Integer j) {
		return valoraciones.get(j);
	}

	public static Integer getPrecioCategoria(Integer i, Integer j) {
		return categoriasProductos.get(i).equals(j) ? getPrecioProducto(i) : 0;
	}

	public static Integer perteneceCategoria(Integer i, Integer j) {
		return getCategoriaProducto(i) == j ? 1 : 0;
	}

	public static void model_ejercicio2() throws IOException {

		for (int k = 1; k <= 3; k++) {
			DatosProductos.iniDatos("Ficheros/Ejercicio2DatosEntrada" + k + ".txt");
			preciosProductos = DatosProductos.getPrecios();
			categoriasProductos = DatosProductos.getCategorias();
			valoraciones = DatosProductos.getValoraciones();
			presupuesto = DatosProductos.getPresupuesto();

			AuxGrammar.generate(EJ2_PLE.class, "lsi_models/Ejercicio2.lsi", "gurobi_models/Ejercicio2_" + k + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio2_" + k + ".lp");
			Locale.setDefault(Locale.of("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}

	}

	public static void main(String[] args) throws IOException {
		model_ejercicio2();
	}

}
