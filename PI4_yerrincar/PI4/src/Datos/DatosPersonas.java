package Datos;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosPersonas {
	public static List<Integer> edad;
	public static List<List<String>> idioma;
	public static List<String> nacionalidad;
	public static List<List<Integer>> afinidad;

	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		edad = List2.empty();
		idioma = List2.empty();
		nacionalidad = List2.empty();
		afinidad = List2.empty();
		List<String> aux1 = List2.empty();
		List<Integer> aux2 = List2.empty();
		for (int i = 1; i < lineas.size(); i++) {
			String l = lineas.get(i);
			int indice = i - 1;
			String[] s1 = l.split(";");
			edad.add(Integer.parseInt(s1[0].split("=")[1]));
			for (String idioma : s1[1].split("=")[1].trim().split(",")) {
				aux1.add(idioma.trim());
			}
			idioma.add(aux1);
			aux1 = List2.empty();
			nacionalidad.add(s1[2].split("=")[1]);
			String[] s2 = s1[3].split("=")[1].split(",");
			for (int j = 0; j < s2.length; j++) {
				String s = s2[j].replace("(", "").replace(")", "").split(":")[1];
				if (j == indice && j <= indice) {
					aux2.add(0);
					aux2.add(Integer.parseInt(s));
				} else {
					aux2.add(Integer.parseInt(s));
				}
				if (i == lineas.size() - 1 && j == lineas.size() - 3) {
					aux2.add(0);
				}
			}
			afinidad.add(aux2);
			aux2 = List2.empty();
		}
		toConsole();
	}

	private static void toConsole() {
		System.out.println(
				"Edad: " + edad + " Idioma: " + idioma + "\n Nacionalidad " + nacionalidad + " Afinidad: " + afinidad);
	}

	public static List<Integer> getEdad() {
		return edad;
	}

	public static List<List<String>> getIdioma() {
		return idioma;
	}

	public static List<List<Integer>> getAfinidad() {
		return afinidad;
	}

	public static List<String> getNacionalidad() {
		return nacionalidad;
	}

	public static Integer getNumPersonas() {
		return edad.size();
	}

	public static Integer getEdadI(Integer i) {
		return edad.get(i);
	}

	public static List<String> getIdiomaListaI(Integer i) {
		return idioma.get(i);
	}

	public static Boolean MismoIdioma(Integer i, Integer j) {
		Boolean res = false;
		for (String s : getIdiomaListaI(i)) {
			if (getIdiomaListaI(j).contains(s)) {
				res = true;
			}
		}
		return res;
	}

	public static Integer getAfinidadIJ(Integer i, Integer j) {
		return getAfinidad().get(i).get(j);
	}

	public static String getNacionalidadI(Integer i) {
		return nacionalidad.get(i);
	}

	public static void main(String[] args) {

		for (int i = 1; i < 4; i++) {
			String ruta = "Ficheros/Ejercicio4DatosEntrada" + i + ".txt";
			System.out.println("DATOS - EJERCICIO 4, APARTADO " + i);
			iniDatos(ruta);
		}
	}
}
