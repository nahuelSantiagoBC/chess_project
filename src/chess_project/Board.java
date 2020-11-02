package chess_project;

public class Board {

	//propiedades
	Token[][] board = new Token[8][8];
	
	Token whiteKing;
	int whiteCapturedTokens = 0;
	int whiteScore = 0;
	Token[] whitePrison = new Token[8];
	
	Token blackKing;
	int blackCapturedTokens = 0;
	int blackScore = 0;
	Token[] blackPrison = new Token[8];
	
	
	//
	public Board() {
		
		//inicializamos tablero
		this.initBoard();
		
		
		
	}
	
	public void initBoard() {
		
		//inicializamos blancas
		whiteKing = new Token('K');
		board[0][0] = new Token('T');
		board[0][1] = new Token('C');
		board[0][2] = new Token('A');
		board[0][3] = new Token('Q');
		board[0][4] = whiteKing;
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
		blackKing = new Token('k');
		board[7][0] = new Token('t');
		board[7][1] = new Token('c');
		board[7][2] = new Token('a');
		board[7][3] = new Token('q');
		board[7][4] = blackKing;
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
	
	private Token getToken(int[] tokenLocation) {
		
		return board[tokenLocation[0]][tokenLocation[1]];
	}
	
	//funciones
	
	private boolean checkMove(int[] initLocation, int[] targetLocation) {
		Token movingToken = getToken(initLocation);
		Token targetToken;
		boolean result = false;
		//booleana que comprueba que el lugar al que se quiere mover está en la tabla
		boolean offBoard = (targetLocation[0] > 7 || targetLocation[0] < 0 || targetLocation[1] > 7 || targetLocation[1] < 0);
		
		if(!offBoard) {
			//como sabemos que está en el tablero, obtenemos el token que hay en la posición a moverse
			targetToken = getToken(targetLocation);
			boolean isEmpty = targetToken.getType() == 'e';
			boolean isEnemy = Character.isUpperCase(targetToken.getType()) != Character.isUpperCase(movingToken.getType());
						
			if (isEmpty || (!isEmpty) && (isEnemy)) {
				//comprobamos que el movimiento cumpla el patron de la ficha
				if (movingToken.checkTokenPattern(targetLocation)) {
					result = true;
				}				
			}
		}
		
		return result;
	}
	
	private void makeMove(int[] initLocation, int[] targetLocation) {
		Token targetToken = getToken(targetLocation);
		boolean isEmpty = targetToken.getType() == 'e';
		
		if(!isEmpty) {
			captureToken(targetToken);
		}
		moveToken(initLocation, targetLocation);
	}
	
	private boolean isOffBoard(int[] location) {
		
		return location[0] > 7 || location[0] < 0 || location[1] > 7 || location[1] < 0;
	}
	
	private void moveToken(int[] initLocation, int[] targetLocation) {
		Token initToken = getToken(initLocation);
		Token targetToken = getToken(targetLocation);
		board[initLocation[0]][initLocation[1]] = initToken;
		board[targetLocation[0]][targetLocation[1]] = targetToken;
	}
	
	private void captureToken(Token token) {
		if(Character.isUpperCase(token.getType())) {
			blackPrison[blackCapturedTokens] = token;
			blackCapturedTokens++;
			blackScore += token.getValue();
		} else {
			whitePrison[whiteCapturedTokens] = token;
			whiteCapturedTokens++;
			whiteScore += token.getValue();
		}
	}
	
	
}
