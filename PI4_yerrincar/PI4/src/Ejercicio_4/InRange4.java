	package Ejercicio_4;

import java.util.List;
import java.util.Set;

import Datos.DatosPersonas;
import Soluciones.SolucionPersonas;
import us.lsi.ag.RangeIntegerData;
import us.lsi.common.Set2;

public class InRange4 implements RangeIntegerData<SolucionPersonas> {

	public InRange4(String s) {
		DatosPersonas.iniDatos(s);
	}

	@Override

	public Integer max(Integer i) {
		return DatosPersonas.getNumPersonas();
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	@Override
	public Integer size() {
		return DatosPersonas.getNumPersonas();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double goal = 0.0;
		Double error = 0.0;
		for (int i = 0; i < value.size(); i++) {
			error += value.get(value.get(i)).equals(i) ? 0 : 1;
			goal += DatosPersonas.getAfinidadIJ(i, value.get(i));
			error += DatosPersonas.MismoIdioma(i, value.get(i)) ? 0 : 1;
			if (value.size() % 2 == 0) {
				error += value.get(i).equals(i) ? 1 : 0;
			}
			error += Math.abs(DatosPersonas.getEdadI(i) - DatosPersonas.getEdadI(value.get(i))) <= 5 ? 0 : 1;
			if (value.size() % 2 == 0 && value.get(i).equals(i)) {
				error += DatosPersonas.getNacionalidadI(i).equals(DatosPersonas.getNacionalidadI(value.get(i))) ? 1 : 0;
			}
		}
		return goal - error * error * 1000;
	}

	@Override
	public SolucionPersonas solucion(List<Integer> value) {
		SolucionPersonas res = SolucionPersonas.empty();
		for (int i = 0; i < value.size(); i++) {
			Set<Integer> s = Set2.of(i, value.get(i));
			res.add(s);
		}
		return res;
	}

}
