package Datos;

import java.util.LinkedList;
import java.util.List;

import us.lsi.common.Files2;

public class DatosDistribuidor {
	public static List<Integer> destinos;
	public static List<Integer> productos;
	public static List<List<Integer>> precios;

	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		destinos = new LinkedList<>();
		productos = new LinkedList<>();
		precios = new LinkedList<>();
		List<Integer> aux = new LinkedList<>();
		for (int i = 0; i < lineas.size(); i++) {
			String l = lineas.get(i);
			if (!l.contains("/") && l.contains("->")) {
				String[] v = l.split(";");
				productos.add(Integer.parseInt(v[0].split("=")[1]));
				String preciosAux = v[1].split("=")[1];
				for (String s : preciosAux.split(",")) {
					s = s.trim();
					s = s.replace("(", "");
					s = s.replace(")", "").trim();
					aux.add(Integer.parseInt(s.split(":")[1]));
				}
				precios.add(aux);
				aux = new LinkedList<>();
			} else if (!l.contains("/") && l.contains(";")) {
				String[] v = l.replace(";", "").split("=");
				destinos.add(Integer.parseInt(v[1]));
			}
		}
		toConsole();
	}

	private static void toConsole() {
	System.out.println("destinos: " + destinos + " productos: " + productos + " precios: " + precios);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
		iniDatos("ficheros/Ejercicio3DatosEntrada2.txt");
		iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
	}

	public static List<Integer> getdestinos() {
		return destinos;
	}

	public static List<Integer> getproductos() {
		return productos;
	}

	public static List<List<Integer>> getprecios() {
		return precios;
	}
	
}
