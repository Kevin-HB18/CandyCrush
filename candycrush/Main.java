package candycrush;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		int iOrigen, jOrigen, iDestino, jDestino;
		// Scanner

		Scanner in = new Scanner(System.in);

		// Inicio Juego 

		System.out.println ("CANDY CRUSH\n");
		System.out.print("Ingresa tu nombre: ");

		CandyCrush juego = new CandyCrush(in.next().toUpperCase());
		
		juego.printTablero();
		System.out.println("[ Empecemos... ]\n");

		while (true) {

			if (!juego.seguirJuego()) break;

			System.out.println("Ingresa Fila y Columna de origen:");
			System.out.print("Fila(horizontal): ");
			iOrigen = in.nextInt();
			System.out.print("Columna(vertical): ");
			jOrigen = in.nextInt();

			System.out.println("Ingresa Fila y Columna de destino:");
			System.out.print("Fila(horizontal): ");
			iDestino = in.nextInt();
			System.out.print("Columna(vertical): ");
			jDestino = in.nextInt();

			if (juego.mover(iOrigen, jOrigen, iDestino, jDestino)) juego.printTablero();
				
			
			else  {
				juego.printTablero();
				System.out.println("[ Movimiento invalido, intenta de nuevo ]");
			}
			
		
		}	

		juego.resultado();
		in.close();
	
}
}
