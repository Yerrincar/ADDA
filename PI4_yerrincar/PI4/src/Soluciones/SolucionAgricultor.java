package Soluciones;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Pair;

public class SolucionAgricultor {

	List<Pair<Integer,Integer>> variedades;

	
	private SolucionAgricultor() {
		
		variedades = new LinkedList<Pair<Integer, Integer>>();
	}
	 
	public static SolucionAgricultor empty() {
		return new SolucionAgricultor();
	}
	
	public String toString() {
		
		String s = variedades.stream().map(v -> "Huerto " + v.first()+  ": V"+ v.second())
				.collect(Collectors.joining("\n",
						"Reparto de verduras y huerto en el que es plantada cada una de ellas (si procede)"
						+ variedades +":\n","\n"));
		
		return s;
		
	}

	public void add(int i, Integer j) {
		variedades.add(Pair.of(i,j));	
	}
}