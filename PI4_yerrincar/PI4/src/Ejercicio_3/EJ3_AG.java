package Ejercicio_3;


import java.util.List;
import java.util.Locale;

import Soluciones.SolucionDistribuidor;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

public class EJ3_AG {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
	
		AlgoritmoAG.ELITISM_RATE = 0.1;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
	
		for(int i = 1; i <4; i++) {
		
			InRange3 h = new InRange3("Ficheros/Ejercicio3DatosEntrada" + i + ".txt");
			AlgoritmoAG<List<Integer>, SolucionDistribuidor> ap = AlgoritmoAG.of(h);
		
			ap.ejecuta();
		
			System.out.println("\n");
			System.out.println(ap.bestSolution());
			System.out.println("\n");
		}
	}
}