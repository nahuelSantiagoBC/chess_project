package chess_project;

import java.util.Scanner;




public class ChessTester {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Board board = new Board();		//Inicializamos el tablero
		boolean noKingCaptured = true;	//Booleano para acabar
		boolean whiteplayer = true;		//Booleano con el cual controlamos los jugadores
		int location[] = new int[2];	//Array que guarda las posiciones iniciales
		int targetLocation[] = new int[2];	//Array que guarda la posicion de adonde moveremos la ficha

		
		//printamos el tablero
		board.showBoard();
		
		
		//Bucle del juego que no acaba hasta que no se se capture a un rey
		while(noKingCaptured) {

			//bucle en el que pediremos las coordenadas de la fichas y comprobamos que este en el rango
			do {
				//turno de las blancas
				if(whiteplayer) {
					System.out.println("White turn");
					System.out.println("Which token you want to move");
					//Comprobamos que sea una ficha blanca
					do {
							
						location[0] = scan.nextInt();
						location[1] = scan.nextInt();
						if(!Character.isUpperCase(board.getToken(location).getType())) {
							System.out.println("Wrong, choose a white token");
						}
					
					//Mientras no sea mayuscula es decir blanca seguiremos pidiendo una ficha correcta	
					}while(!Character.isUpperCase(board.getToken(location).getType()));
				
					
				}
				//Turno de las negras
				if(!whiteplayer){
					System.out.println("black turn");
					System.out.println("Which token you want to move");
					//Comprobamos que eliga una ficha negra
					do {
						
						location[0] = scan.nextInt();
						location[1] = scan.nextInt();
						if(!Character.isUpperCase(board.getToken(location).getType())) {
							System.out.println("Wrong, choose a white token");
						}
						//Repetimos hasta que sea correcta
					}while(!Character.isLowerCase(board.getToken(location).getType()));

					
				}
				
			//Comprobamos que nos introduzca una posicion en el tablero	
			}while(board.isOffBoard(location));

			//Bucle para comprobar que a donde quiere moverse sea una posicion dentro de tablero
			do {
				//Para las blancas
				if(whiteplayer) {

					
					System.out.println("Where do you want to move");
					targetLocation[0] = scan.nextInt();
					targetLocation[1] = scan.nextInt();
					
				}
				//Para las negras
				if(!whiteplayer){

					
					System.out.println("Where do you want to move");
					targetLocation[0] = scan.nextInt();
					targetLocation[1] = scan.nextInt();


				}
				//y comprobamos que esta en el tablero
			}while(board.isOffBoard(targetLocation));

			
			if(board.checkMove(location, targetLocation)) {
				board.makeMove(location, board.getToken(targetLocation));
				whiteplayer = !whiteplayer;
			}
			else {
				System.out.println("Wrong move, do it again");
			}
				


			
			board.showBoard();
			noKingCaptured = board.isKingCapture();

		}
	}
}
