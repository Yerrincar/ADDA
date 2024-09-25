package Ejercicio_3;

import java.util.List;

import Datos.DatosDistribuidor;
import Soluciones.SolucionDistribuidor;
import us.lsi.ag.RangeIntegerData;
import us.lsi.common.List2;

public class InRange3 implements RangeIntegerData<SolucionDistribuidor> {
	public InRange3(String fichero) {
		DatosDistribuidor.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosDistribuidor.getdestinos().size() * DatosDistribuidor.getproductos().size();
	}

	

	public Double fitnessFunction(List<Integer> value) {
		Double goal = 0.0;
		Double error1 = 0.0;
		Double error2 = 0.0;
		List<Integer> cantidadesProducto = List2.empty();
		List<Integer> cantidadesDestinos = List2.empty();
		DatosDistribuidor.getproductos().stream().forEach(a -> cantidadesProducto.add(0));
		DatosDistribuidor.getdestinos().stream().forEach(a -> cantidadesDestinos.add(0));
		for (int i = 0; i < value.size(); i++) {
			cantidadesDestinos.set(i / DatosDistribuidor.getproductos().size(),
					cantidadesDestinos.get(i / DatosDistribuidor.getproductos().size()) + value.get(i));
			cantidadesProducto.set(i % DatosDistribuidor.getproductos().size(),
					cantidadesProducto.get(i % DatosDistribuidor.getproductos().size()) + value.get(i));
			goal += DatosDistribuidor.getprecios().get(i % DatosDistribuidor.getproductos().size())
					.get(i / DatosDistribuidor.getproductos().size()) * value.get(i);
		}
		for (int i = 0; i < DatosDistribuidor.getproductos().size(); i++) {
			error1 += cantidadesProducto.get(i) > DatosDistribuidor.getproductos().get(i) ? 1 : 0;
		}
		for (int i = 0; i < DatosDistribuidor.getdestinos().size(); i++) {
			error2 += cantidadesDestinos.get(i) < DatosDistribuidor.getdestinos().get(i) ? 1 : 0;
		}
		Double error = error1 * error1 + error2 * error2;
		return -goal - error * error * 50243056;
	}

	@Override
	public SolucionDistribuidor solucion(List<Integer> value) {
		SolucionDistribuidor res = SolucionDistribuidor.empty();
		System.out.println(value);
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0)
				res.add(i / DatosDistribuidor.getproductos().size(), i % DatosDistribuidor.getproductos().size(),
						value.get(i));
		}
		return res;
	}

	@Override
	public Integer max(Integer i) {
		Integer demandaMaxima = DatosDistribuidor.getdestinos().get(i / DatosDistribuidor.getproductos().size());
		Integer productosMaximas = DatosDistribuidor.getproductos().get(i % DatosDistribuidor.getproductos().size());
		return demandaMaxima > productosMaximas ? productosMaximas + 1 : demandaMaxima + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}