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
		board[0][0] = new Token('T');
		board[0][1] = new Token('C');
		board[0][2] = new Token('A');
		board[0][3] = new Token('Q');
		board[0][4] = new Token('K');
		board[0][5] = new Token('A');
		board[0][6] = new Token('C');
		board[0][7] = new Token('T');
		
		board[1][0] = new Token('P');
		board[1][1] = new Token('P');
		board[1][2] = new Token('P');
		board[1][3] = new Token('P');
		board[1][4] = new Token('P');
		board[1][5] = new Token('P');
		board[1][6] = new Token('P');
		board[1][7] = new Token('P');
		
		
		//inicializamos negras
		board[7][0] = new Token('t');
		board[7][1] = new Token('c');
		board[7][2] = new Token('a');
		board[7][3] = new Token('q');
		board[7][4] = new Token('k');
		board[7][5] = new Token('a');
		board[7][6] = new Token('c');
		board[7][7] = new Token('t');
		
		board[6][0] = new Token('p');
		board[6][1] = new Token('p');
		board[6][2] = new Token('p');
		board[6][3] = new Token('p');
		board[6][4] = new Token('p');
		board[6][5] = new Token('p');
		board[6][6] = new Token('p');
		board[6][7] = new Token('p');
		
		//Autocompletamos lo que queda del tablero
		
		for (int i = 2; i <= 6; i++) {
			System.out.println();
			for (int j = 0; j <= 8; j++) {
				
				board[i][j] = new Token();
				//Esto es un intento de intentar printar el tipo de ficha que es pero como es una matriz de objetos, que guar un objeto posicion y luego guarda un objeto ficha no se
				//como pillar el caracter de la ficha
				System.out.println(getToken(new int[]{i, j}));
			}
			
		}
	}
	
	public Token getToken(int[] tokenLocation) {
		
		return board[tokenLocation[0]][tokenLocation[1]];
	}
	
	//funciones
	
	public boolean checkMove(int[] initLocation, int[] targetLocation) {
		Token token = getToken(initLocation);
		boolean result = false;
		//booleana que comprueba que el lugar al que se quiere mover está en la tabla
		boolean offBoard = (targetLocation[0] > 7 || targetLocation[0] < 0 || targetLocation[1] > 7 || targetLocation[1] < 0);
		
		if(!offBoard) {
			if (token.checkTokenPattern(targetLocation)) {
				
			}
		}
		
		return result;
	}
	
	
	
}
