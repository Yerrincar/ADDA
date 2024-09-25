package Soluciones;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Datos.DatosPersonas;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class SolucionPersonas {
	private Set<Set<Integer>> parejas;

	public static SolucionPersonas empty() {
		return new SolucionPersonas();
	}

	private SolucionPersonas() {
		parejas = Set2.empty();
	}

	public String toString() {
		Set<List<Integer>> auxiliar = parejas.stream().map(s -> List2.ofCollection(s)).collect(Collectors.toSet());
		Set<List<Integer>> toRemove = Set2.empty();
		for (List<Integer> l : auxiliar) {
			if (l.size() == 1) {
				toRemove.add(l);
			}
		}
		auxiliar.removeAll(toRemove);
		Integer afinidad = auxiliar.stream().mapToInt(t -> DatosPersonas.getAfinidadIJ(t.get(0), t.get(1))).sum();
		String s = auxiliar.stream()
				.map(p -> "Pareja formal por: " + "Persona 1 " + p.get(0) + "y persona 2 " + p.get(1))
				.collect(Collectors.joining("\n", "Parejas: \n", "\n con afinidad total: " + afinidad + " \n"));
		return s;
	}

	public void add(Set<Integer> pareja) {
		parejas.add(pareja);
	}
}
