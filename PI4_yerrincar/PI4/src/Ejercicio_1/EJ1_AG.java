package Ejercicio_1;

import java.util.List;
import java.util.Locale;

import Soluciones.SolucionAgricultor;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class EJ1_AG {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.1;
		AlgoritmoAG.CROSSOVER_RATE = 0.9;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		for(int k = 1; k <= 3; k++) {
			InRange s = new InRange("Ficheros/Ejercicio1DatosEntrada" + k + ".txt");
			AlgoritmoAG<List<Integer>, SolucionAgricultor> ag = AlgoritmoAG.of(s);
			ag.ejecuta();
			
			System.out.println("\n");
			System.out.println(ag.bestSolution());
			System.out.println("\n");
			
		}
	}

}
