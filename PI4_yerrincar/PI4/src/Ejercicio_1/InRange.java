package Ejercicio_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Datos.DatosAgricultor;
import Soluciones.SolucionAgricultor;
import us.lsi.ag.RangeIntegerData;
import us.lsi.common.Set2;

public class InRange implements RangeIntegerData<SolucionAgricultor> {

	public InRange(String file) {
		DatosAgricultor.iniDatos(file);
	}

	@Override
	public Integer size() {
		return DatosAgricultor.getNumVariedades();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double goal = 0.0;
		Double error = 0.0;
		Map<Integer, Set<Integer>> dicc = new HashMap<>();
		for (Integer i = 0; i < value.size(); i++) {
			Integer aux = value.get(i);
			if (dicc.containsKey(aux)) {
				Set<Integer> auxSet = dicc.get(aux);
				auxSet.add(i);
				dicc.put(aux, auxSet);
				Integer num = i;
				error += dicc.get(aux).stream().anyMatch(a -> DatosAgricultor.getIncompatibilidad(num, a) == 1) ? 1 : 0;
			} else if (aux >= 0) {
				Set<Integer> s = Set2.of(i);
				dicc.put(aux, s);
			}
		}
		for (Integer i : value) {
			if (i < DatosAgricultor.getNumHuertos()) {
				Integer suma = dicc.get(i).stream().mapToInt(a -> DatosAgricultor.getVariedadI(a)).sum();
				if (suma > DatosAgricultor.getHuertoI(i)) {
					error++;
				}
			}
		}
		goal += value.stream().filter(v -> v < DatosAgricultor.getNumHuertos()).count();
		return goal - error * error * 1000;
	}

	@Override
	public SolucionAgricultor solucion(List<Integer> value) {
		SolucionAgricultor res = SolucionAgricultor.empty();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) < DatosAgricultor.getNumHuertos())
				res.add(i, value.get(i));
		}
		return res;
	}

	@Override
	public Integer max(Integer i) {
		return DatosAgricultor.getNumHuertos() + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

}
