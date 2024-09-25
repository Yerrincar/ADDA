package Datos;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosProductos {

	public static Integer presupuesto;
	public static List<Integer> preciosProductos;
	public static List<Integer> categoriasProductos;
	public static List<Integer> valoraciones;

	public static void iniDatos(String file) {
		List<String> lines = Files2.linesFromFile(file);
		preciosProductos = new LinkedList<Integer>();
		categoriasProductos = new LinkedList<Integer>();
		valoraciones = new LinkedList<Integer>();

		for (int k = 0; k < lines.size(); k++) {
			String linea = lines.get(k);
			if (k == 0) {
				presupuesto = Integer.parseInt(linea.split("=")[1].trim());
			} else if (k >= 2) {
				String[] p = linea.split(":");
				categoriasProductos.add(Integer.parseInt(p[2].trim()));
				preciosProductos.add(Integer.parseInt(p[1].trim()));
				valoraciones.add(Integer.parseInt(p[3].trim()));
			}
		}
		toConsole();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			System.out.println("Fichero de entrada: " + (i + 1));
			iniDatos("Ficheros/Ejercicio2DatosEntrada" + (i + 1) + ".txt");
			System.out.println("\n");
		}
	}

	public static Integer getPresupuesto() {
		return presupuesto;
	}

	public static List<Integer> getCategorias() {
		return categoriasProductos;
	}
	public static List<Integer> getValoraciones(){
		return valoraciones;
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

	private static void toConsole() {
		Integer lim = preciosProductos.size();
		for (Integer i = 0; i < lim; i++) {
			System.out.println("Precios: " + preciosProductos.get(i) + "\nPresupuesto disponible: " + presupuesto);
		}
	}
}