package chess_project;

import java.util.Scanner;




public class ChessTester {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Board board = new Board();
		boolean noKingCaptured = true;
		boolean whiteplayer = true;
		int location[] = new int[2];
		int targetLocation[] = new int[2];

		
		board.showBoard();
		
		

		while(noKingCaptured) {

			//preguntamos que pieza quiere mover
			do {
				if(whiteplayer) {
					do {

						System.out.println("White turn");
						System.out.println("Which token you want to move");
						location[0] = scan.nextInt();
						location[1] = scan.nextInt();
					
						
					}while(!Character.isUpperCase(board.getToken(location).getType()));
				
					
				}
				if(!whiteplayer){
					do {
						System.out.println("black turn");
						System.out.println("Which token you want to move");
						location[0] = scan.nextInt();
						location[1] = scan.nextInt();
						
					}while(!Character.isLowerCase(board.getToken(location).getType()));

					
				}
				

			}while(board.isOffBoard(location));

			do {
				if(whiteplayer) {

					
					System.out.println("Where do you want to move");
					targetLocation[0] = scan.nextInt();
					targetLocation[1] = scan.nextInt();
					
				}
				if(!whiteplayer){

					
					System.out.println("Where do you want to move");
					targetLocation[0] = scan.nextInt();
					targetLocation[1] = scan.nextInt();


				}
			}while(board.isOffBoard(targetLocation));

			
			if(board.checkMove(location, targetLocation)) {
				board.getToken(location).move(board, board.getToken(targetLocation));
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
