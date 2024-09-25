package Datos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;

public class DatosAgricultor {

	private static List<Integer> huertos;
	private static List<Integer> variedades;
	private static Set<Set<Integer>> incompatibility;

	public record Huerto(String nombre, Integer metrosDisponibles) {
		public static Huerto of(String nombre, Integer metrosDisponibles) {
			return new Huerto(nombre, metrosDisponibles);
		}

		public static Huerto parseaHuerto(String cadena) {
			String[] partes = cadena.split(":");

			String nombre = partes[0].trim();

			String[] p = partes[1].trim().split("=");
			String metrosDisponibles = p[1].trim().replace(";", "").trim();

			return Huerto.of(nombre, Integer.valueOf(metrosDisponibles));
		}
	}

	public record Variedad(String nombre, Integer metrosRequeridos, List<String> comp) {

		public static Variedad of(String nombre, Integer metrosRequeridos, List<String> comp) {
			return new Variedad(nombre, metrosRequeridos, comp);
		}

		public static Variedad parseaVariedad(String cadena) {
			String[] partes = cadena.split("->");
			String nombre = partes[0].trim();
			String[] p = partes[1].split(";");
			String[] p2 = p[0].split("=");
			String metrosRequeridos = p2[1].trim();
			String cadenaComponentes = p[1].trim();
			List<String> comp = listaVariedades(cadenaComponentes);
			return Variedad.of(nombre, Integer.valueOf(metrosRequeridos), comp);
		}

		private static List<String> listaVariedades(String cadena) {
			List<String> res = new ArrayList<>();
			String[] partes = cadena.split("=");

			String[] p = partes[1].trim().split(",");
			for (String cadVariedad : p) {
				String v = cadVariedad;
				res.add(v);
			}
			return res;
		}

	}

	public static void iniDatos(String file) {
		List<String> listaHuertos = Files2.linesFromFile(file);
		huertos = new LinkedList<>();
		variedades = new LinkedList<>();
		incompatibility = new HashSet<>();
		for (int i = 0; i < listaHuertos.size(); i++) {
			String l = listaHuertos.get(i);
			if (l.contains(":")) {
				String[] v = l.replace(";", "").split("=");
				huertos.add(Integer.parseInt(v[1]));
			} else if (l.contains("->")) {
				String[] v = l.split(";");
				String[] v2 = v[0].split("=");
				variedades.add(Integer.parseInt(v2[1]));
				v2 = v[1].split("=");
				String aux = v2[1].replaceAll("V", "");
				;
				Set<Set<Integer>> incom = new HashSet<>();
				for (String s : aux.split(",")) {
					Set<Integer> pareja = new HashSet<Integer>();
					pareja.add(variedades.size() - 1);
					pareja.add(Integer.parseInt(s));
					incom.add(pareja);
				}
				incompatibility.addAll(incom);
			}
		}
		toConsole();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			System.out.println("Fichero de entrada: " + (i + 1));
			iniDatos("Ficheros/Ejercicio1DatosEntrada" + (i + 1) + ".txt");
			System.out.println("\n");
		}
	}

	public static List<Integer> getHuertos() {
		return huertos;
	}

	public static Integer getHuertoI(Integer i) {
		return huertos.get(i);
	}
	
	public static Integer getVariedadI(Integer i) {
		return variedades.get(i);
	}


	public static Integer getNumHuertos() {
		return huertos.size();
	}
	
	
	public static List<Integer> getVariedades() {
		return variedades;
	}

	public static Integer getNumVariedades() {
		return variedades.size();
	}

	public static Integer getIncompatibilidad(Integer i, Integer k) {
		Set<Integer> conj = new HashSet<>();
		conj.add(i);
		conj.add(k);
		return incompatibility.contains(conj) ? 1 : 0;
	}

	public static Set<Set<Integer>> getVarCompatibilidad() {
		return incompatibility;
	}

	private static void toConsole() {
		System.out.println("Variedades disponibles: " + variedades + "\nMetros cuadrados disponibles por huerto: "
				+ huertos + "NumHuertos: " + getNumHuertos());

	}

}
