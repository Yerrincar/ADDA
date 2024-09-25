package Soluciones;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import Datos.DatosProductos;

public class SolucionProductos {

	private List<Integer> productos;
	
	private SolucionProductos() {
		productos = new LinkedList<>();
	}

	public String toString() {
		Integer suma = productos.stream().mapToInt(p -> DatosProductos.getPrecioProducto(p)).sum();
		String s = productos.stream().map(p -> "Producto" + p)
				.collect(Collectors.joining(", ", "{", "} \n Precio Total:" + suma));
		return s;
	}
	
	public static SolucionProductos empty() {
		return new SolucionProductos();
	}
	
	public void add(int i) {
		productos.add(i);
	}
}
