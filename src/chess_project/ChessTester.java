package chess_project;

import java.util.Scanner;

import com.sun.webkit.ContextMenu.ShowContext;

public class ChessTester {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Board panel = new Board();
		boolean kingCaptured = false;
		int jugador = 1;
		int location[] = new int[2];
		int targetLocation[] = new int[2];

		
		panel.showBoard();


		while(!kingCaptured) {

			//preguntamos que pieza quiere mover
			do {
				if(jugador % 2 ==1) {

					System.out.println("White turn");
					System.out.println("Which token you want to move");
					location[0] = scan.nextInt();
					location[1] = scan.nextInt();

				}
				else{

					System.out.println("black turn");
					System.out.println("Which token you want to move)");
					location[0] = scan.nextInt();
					location[1] = scan.nextInt();
				}

			}while(panel.isOffBoard(location));

			do {
				if(jugador % 2 ==1) {

					System.out.println("White turn");
					System.out.println("Where do you want to move");
					location[0] = scan.nextInt();
					location[1] = scan.nextInt();

				}
				else{

					System.out.println("black turn");
					System.out.println("Where do you want to move");
					location[0] = scan.nextInt();
					location[1] = scan.nextInt();


				}
			}while(panel.isOffBoard(location));






		}
	}
}
