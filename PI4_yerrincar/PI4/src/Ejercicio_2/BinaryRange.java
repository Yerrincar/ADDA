package Ejercicio_2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Datos.DatosProductos;
import Soluciones.SolucionProductos;
import us.lsi.ag.BinaryData;

public class BinaryRange implements BinaryData<SolucionProductos> {

	public BinaryRange(String file) {
		DatosProductos.iniDatos(file);
	}

	@Override
	public Integer size() {
		return DatosProductos.getPrecios().size();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0., error = 0.;
		Map<Integer, List<Integer>> dicc = new HashMap<>();
		List<Integer> productos = new LinkedList<>();
		List<Integer> aux = new LinkedList<>();
		for (int i = 0; i < DatosProductos.getNumCategorias(); i++) {
			dicc.put(i, new LinkedList<>());
		}
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) == 1) {
				productos.add(i);
				goal += DatosProductos.getPrecioProducto(i);
				Integer categoria = DatosProductos.getCategoriaProducto(i);
				aux = dicc.get(categoria);
				aux.add(DatosProductos.getPrecioProducto(i));
				dicc.put(categoria, aux);
			}
		}
		for (List<Integer> l : dicc.values()) {
			error += l.stream().mapToInt(p -> p).sum() > DatosProductos.getPresupuesto() ? 1 : 0;
		}
		error += dicc.values().stream().filter(l -> l.isEmpty()).count();
		error += productos.stream().mapToDouble(p -> DatosProductos.getValoracionProducto(p)).average().orElse(0) >= 3
				? 0
				: 5;
		return -goal - 10000 * error * error;
	}

	@Override
	public SolucionProductos solucion(List<Integer> value) {
		SolucionProductos res = SolucionProductos.empty();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) == 1) {
				res.add(i);
			}
		}
		return res;
	}

}
