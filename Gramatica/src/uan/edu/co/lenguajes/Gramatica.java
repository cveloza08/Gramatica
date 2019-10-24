package uan.edu.co.lenguajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import uan.edu.co.lenguajes.Variable;

public class Gramatica {

	private List<Variable> gramaticaInicial = new ArrayList<>();
	private List<Variable> terminales = new ArrayList<>();
	static Scanner in = new Scanner(System.in);

	public void verificadorTerminal(List<Variable> v) {
		for (int i = 0; i < v.size(); i++) {
			if (verificarVariableTerminal(v.get(i).getProduccion())) {
				terminales.add(v.get(i));
			}
		}

		System.out.println("\nGramatica inicial");
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i).getVariable() + "->" + v.get(i).getProduccion());
		}

		System.out.println("\nGramatica de Solo variables terminales");
		for (int i = 0; i < eliminarRepetidas(terminales).size(); i++) {
			System.out.println(eliminarRepetidas(terminales).get(i).getVariable() + "->"
					+ eliminarRepetidas(terminales).get(i).getProduccion());
		}

		System.out.println("\nGramatica TERM final");
		List<Variable> aux = new ArrayList<>(); 
		aux = terminalFinal(v, eliminarRepetidas(terminales));
		for (int i = 0; i < aux.size(); i++) {
			System.out.println(aux.get(i).getVariable() + "->" + aux.get(i).getProduccion());
		}

		System.out.println("\nGramatica ALC final");
		List<Variable> aux2 = new ArrayList<>(); 
		aux2 = eliminarRepetidas(alcanzables(aux));		
		for (int i = 0; i < aux2.size(); i++) {
			System.out.println(aux2.get(i).getVariable() + "->" + aux2.get(i).getProduccion());
		}
		
	
	}
	
	
	public List<Variable> alcanzables(List<Variable> v){
		List<Variable> misAlcanzables = new ArrayList<>();
		misAlcanzables.add(v.get(0));
		for (int i = 0; i < v.size(); i++) {
			for (int j = 0; j < v.size(); j++) {
				if(v.get(i).getProduccion().contains(v.get(j).getVariable())) {
					misAlcanzables.add(v.get(i));
				}
			}
			
		}
		return misAlcanzables;
	}

	public List<Variable> terminalFinal(List<Variable> v1, List<Variable> v2) {
		List<Variable> termFinal = new ArrayList<>();
		termFinal = v2;

		for (int i = 0; i < v1.size(); i++) {
			for (int j = 0; j < v2.size(); j++) {
				if (v1.get(i).getProduccion().contains(v2.get(j).getVariable())) {
					termFinal.add(v1.get(i));
				}
			}

		}

		return termFinal;
	}

	public List<Variable> eliminarRepetidas(List<Variable> variables) {

		variables = variables.stream().distinct().collect(Collectors.toList());
		return variables;

	}

	public boolean verificarVariableTerminal(String s) {
		String[] producciones = s.split(",");
		boolean terminable = false;
		for (int i = 0; i < producciones.length; i++) {
			if (verificador(producciones[i])) {
				i = producciones.length;
				terminable = true;

			}
			if (i == producciones.length && terminable == false) {
				terminable = false;
			}
		}

		return terminable;
	}

	public boolean verificador(String s) {

		return s.equals(s.toLowerCase()) || s.equals("");
	}


public void ingresarGramatica() {
	String varProd;
	String[] separador;

	System.out.println("Agregue variable y produccion ejemplo (S->AB,AA,aB ");
	varProd = in.next();
	in.nextLine();

	separador = varProd.split("->");

	Variable a = new Variable();

	a.setVariable(separador[0]);

	a.setProduccion(separador[1]);

	gramaticaInicial.add(a);

	verificadorTerminal(gramaticaInicial);

}
}