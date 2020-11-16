package chess_project;

import java.util.Scanner;
import nahuelslibrary.*;




public class ChessTester {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Board board = new Board();		//Inicializamos el tablero
		boolean noKingCaptured = true;	//Booleano para acabar
		boolean whiteplayer = true;		//Booleano con el cual controlamos los jugadores
		int[] location = new int[2];	//Array que guarda las posiciones iniciales
		int targetLocation[] = new int[2];	//Array que guarda la posicion de adonde moveremos la ficha
		String charLocation;

		//printamos el tablero
		board.showBoard();


		//Bucle del juego que no acaba hasta que no se se capture a un rey
		while(noKingCaptured) {
			//turno de las blancas
			if(whiteplayer) {

				//Pedimos que la posicion de la ficha que quiere
				System.out.println("White turn");
				System.out.println("Choose a token to move");
				
				
				location[0] = (scan.nextInt() - 1);
				charLocation = scan.next();
				location[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;

				//Comprobamos que no sea out of bound y si lo es pedidos hasta que nos de una posicion correcta
				if(board.isOffBoard(location)) {
					do {

						System.out.println("choose a location from 1-8 and from a - h");
						location[0] = (scan.nextInt() - 1);
						charLocation = scan.next();
						location[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;

					}while(board.isOffBoard(location));

				}
				else {
					//Si esta dentro del tablero entonces controlamos que sea una ficha mayuscula es decir blancas
					if(!Character.isUpperCase(board.getToken(location).getType())) {

						do {

							System.out.println("Please choose a white token");
							location[0] = (scan.nextInt() - 1);
							charLocation = scan.next();
							location[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;

						}while(!Character.isUpperCase(board.getToken(location).getType()));

					}
				}	
			}

			//turnos de las negras
			if(!whiteplayer) {

				System.out.println("black turn");
				System.out.println("Choose a token to move");
				location[0] = (scan.nextInt() - 1);
				charLocation = scan.next();
				location[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;

				//Comprobamos que no sea out of bound y si lo es pedidos hasta que nos de una posicion correcta
				if(board.isOffBoard(location)) {
					do {

						System.out.println("choose a location from 1-8 and from a - h");
						location[0] = (scan.nextInt() - 1);
						charLocation = scan.next();
						location[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;

					}while(board.isOffBoard(location));

				}
				//Si esta dentro del tablero entonces controlamos que sea una ficha mayuscula es decir blancas
				else {
					if(!Character.isLowerCase(board.getToken(location).getType()) || board.getToken(location).getType() == 'e') {

						do {

							System.out.println("Please choose a black token");
							location[0] = (scan.nextInt() - 1);
							charLocation = scan.next();
							location[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;


						}while(!Character.isLowerCase(board.getToken(location).getType()) || board.getToken(location).getType()== 'e');

					}
				}	
			}

			//bucle en el que pediremos las coordenadas de la fichas y comprobamos que este en el rango


			//Bucle para comprobar que a donde quiere moverse sea una posicion dentro de tablero
			System.out.println("Where do you want to move the token " + board.getToken(location).getType());
			targetLocation[0] = scan.nextInt() - 1;
			charLocation = scan.next();
			targetLocation[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;
			if(board.isOffBoard(targetLocation)) {

				do {
					System.out.println("Where do you want to move the token " + board.getToken(location).getType());
					targetLocation[0] = scan.nextInt() - 1;
					charLocation = scan.next();
					targetLocation[1] = Character.getNumericValue(charLocation.toLowerCase().charAt(0)) - 10;


				}while(board.isOffBoard(targetLocation));

			}


			//comprobamos que el token el la posicion Location pueda moverse segun su patron a la targetLocation
			//SI es correcto se hara el movimiento y cambiaremos de jugador si es erroneo lo pediremos de nuevo
			if(board.checkMove(location, targetLocation)) {

				board.makeMove(location, board.getToken(targetLocation));

				whiteplayer = !whiteplayer;	//Cambiamos el turno del jugador
			}
			else {
				System.out.println("Wrong move, do it again");
			}


			board.showBoard();
			noKingCaptured = board.isKingCapture();
		}
	}
	public static boolean isInteger(String string) {
		int counter = 0;
		if (string == null || string.isEmpty()) {
			return false;
		}
		if (string.charAt(0) == '-') {
			if (string.length() > 1) {
				counter++;
			} else {
				return false;
			}
		}
		for (; counter < string.length(); counter++) {
			if (!Character.isDigit(string.charAt(counter))) {
				return false;
			}
		}
		return true;

	}
	public static int numberComp(String locationString, Scanner scan) {
		
		if(!isInteger(locationString)) {
			do {
				System.out.println("choose a row from 1 to 8");
				locationString = scan.nextLine();
			}while(!isInteger(locationString));
		}
		
		return Integer.parseInt(locationString) - 1;
	}
}
