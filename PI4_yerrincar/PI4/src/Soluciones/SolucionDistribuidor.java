package Soluciones;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import Datos.DatosDistribuidor;
import us.lsi.common.List2;

public class SolucionDistribuidor {
	private List<List<Integer>> ProductosDestinos;

	public static SolucionDistribuidor empty() {
		return new SolucionDistribuidor();
	}

	private SolucionDistribuidor() {
		ProductosDestinos = new LinkedList<>();
	}

	public void add(Integer destino, Integer producto, Integer cantidad) {
		List<Integer> l = List2.of(destino, producto, cantidad);
		ProductosDestinos.add(l);
	}

	public String toString() {
		Integer coste = ProductosDestinos.stream()
				.mapToInt(l -> DatosDistribuidor.getprecios().get(l.get(1)).get(l.get(0)) * l.get(2)).sum();
		String s = ProductosDestinos.stream()
				.map(l -> "Cantidad de producto" + l.get(1) + " en el destino" + l.get(0) + ": " + l.get(2))
				.collect(Collectors.joining("\n", "Productos enviados a destinos: \n",
						"\n Coste total: " + coste + " \n"));
		return s;
	}
}
