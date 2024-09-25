package Ejercicio_1;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import Datos.DatosAgricultor;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class EJ1_PLE {
	public static List<Integer> huertos;
	public static Set<Set<Integer>> incompatibility;
	public static List<Integer> variedades;

	public static Integer getNumHuertos() {
		return huertos.size();
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

	public static Set<Set<Integer>> getVarCompatibilidad(Integer i, Integer j) {
		return incompatibility;
	}

	public static void model_ejercicio1() throws IOException {

		for (int k = 1; k <= 3; k++) {
			DatosAgricultor.iniDatos("Ficheros/Ejercicio1DatosEntrada" + k + ".txt");
			
			huertos = DatosAgricultor.getHuertos();
			variedades = DatosAgricultor.getVariedades();
			incompatibility = DatosAgricultor.getVarCompatibilidad();
	
			AuxGrammar.generate(EJ1_PLE.class, "lsi_models/Ejercicio1.lsi", "gurobi_models/Ejercicio1_" + k + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1_" + k + ".lp");
			Locale.setDefault(Locale.of("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}

	}

	public static void main(String[] args) throws IOException {
		model_ejercicio1();
	}
}
