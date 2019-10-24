package uan.edu.co.lenguajes;

import java.util.Scanner;

public class Main {

	private static Scanner in;

	public static void main(String[] args) {

		Gramatica gramatica = new Gramatica();
		in = new Scanner(System.in);
		int variables = 0;
		System.out.println("cuantas variables tiene la gramatica:");
		variables = in.nextInt();
		in.nextLine();
		
		for (int i = 0; i < variables; i++) {
			gramatica.ingresarGramatica();
		}

		
	}

}
