package br.com.caelum.livraria.teste;

import java.util.Random;

public class TesteRandom {
	
	public static void main(String[] args) {
		
		Random gerador = new Random();
		
		int resultado = gerador.nextInt(10);
		System.out.println(resultado);
		
		int resultado1 = gerador.nextInt(10);
		System.out.println(resultado1);
		
		Random geradorBoolean = new Random();

		boolean valor = geradorBoolean.nextBoolean();
		System.out.println(valor);

		boolean valor2 = geradorBoolean.nextBoolean();
		System.out.println(valor2);
		
		long millis = System.currentTimeMillis();
		Random geradorBoolean1 = new Random(millis);

		boolean valor1 = geradorBoolean1.nextBoolean();
		System.out.println(valor1);

		boolean valor21 = geradorBoolean1.nextBoolean();
		System.out.println(valor21);
				
		
	}

}
