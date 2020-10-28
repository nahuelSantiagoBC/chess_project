package chess_project;

public class Board {

	//propiedades
	Token[][] board = new Token[8][8];
	
	//
	public Board() {
		
		//inicializamos tablero
		this.initBoard();
		
		
		
	}
	
	public void initBoard() {
		
		//inicializamos whites
		board[0][0] = new Token(false, "white", 'T');
		board[0][1] = new Token(false, "white", 'C');
		board[0][2] = new Token(false, "white", 'A');
		board[0][3] = new Token(false, "white", 'Q');
		board[0][4] = new Token(false, "white", 'K');
		board[0][5] = new Token(false, "white", 'A');
		board[0][6] = new Token(false, "white", 'C');
		board[0][7] = new Token(false, "white", 'T');
		
		board[1][0] = new Token(false, "white", 'P');
		board[1][1] = new Token(false, "white", 'P');
		board[1][2] = new Token(false, "white", 'P');
		board[1][3] = new Token(false, "white", 'P');
		board[1][4] = new Token(false, "white", 'P');
		board[1][5] = new Token(false, "white", 'P');
		board[1][6] = new Token(false, "white", 'P');
		board[1][7] = new Token(false, "white", 'P');
		
		
		//inicializamos negras
		board[7][0] = new Token(false, "black", 't');
		board[7][1] = new Token(false, "black", 'c');
		board[7][2] = new Token(false, "black", 'a');
		board[7][3] = new Token(false, "black", 'q');
		board[7][4] = new Token(false, "black", 'k');
		board[7][5] = new Token(false, "black", 'a');
		board[7][6] = new Token(false, "black", 'c');
		board[7][7] = new Token(false, "black", 't');
		
		board[6][0] = new Token(false, "black", 'p');
		board[6][1] = new Token(false, "black", 'p');
		board[6][2] = new Token(false, "black", 'p');
		board[6][3] = new Token(false, "black", 'p');
		board[6][4] = new Token(false, "black", 'p');
		board[6][5] = new Token(false, "black", 'p');
		board[6][6] = new Token(false, "black", 'p');
		board[6][7] = new Token(false, "black", 'p');
		
		//Autocompletamos lo que queda del tablero
		
		for (int i = 2; i <= 6; i++) {
			System.out.println();
			for (int j = 0; j <= 8; j++) {
				
				board[i][j] = null;
				//Esto es un intento de intentar printar el tipo de ficha que es pero como es una matriz de objetos, que guar un objeto posicion y luego guarda un objeto ficha no se
				//como pillar el caracter de la ficha
				System.out.println(getTokenType(i, j));
			}
			
		}
	}
	
	public char getTokenType(int x, int y) {
		
		return board[x][y].getType();
	}
	
	
}
