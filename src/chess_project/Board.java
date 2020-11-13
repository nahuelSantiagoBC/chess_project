package chess_project;

public class Board {

	//propiedades
	//private Token[][] panel;

	private Token panel[][] = new Token[8][8];
	private Token whiteKing;
	private int whiteCapturedTokens;
	private int whiteScore;
	private Token[] whitePrison;

	private Token blackKing;
	private int blackCapturedTokens;
	private int blackScore;
	private Token[] blackPrison;



	//
	public Board() {

		//inicializamos tablero
		this.initBoard();

		whiteCapturedTokens = 0;
		whiteScore = 0;
		whitePrison = new Token[8];

		blackCapturedTokens = 0;
		blackScore = 0;
		blackPrison = new Token[8];

	}

	public void initBoard() {

		panel = new Token[8][8];

		//inicializamos blancas
		whiteKing = new Token('K', new int[] {0, 4});
		panel[0][0] = new Token('T', new int[] {0, 0});
		panel[0][1] = new Token('C', new int[] {0, 1});
		panel[0][2] = new Token('A', new int[] {0, 2});
		panel[0][3] = new Token('Q', new int[] {0, 3});
		panel[0][4] = whiteKing;
		panel[0][5] = new Token('A', new int[] {0, 5});
		panel[0][6] = new Token('C', new int[] {0, 6});
		panel[0][7] = new Token('T', new int[] {0, 7});

		panel[1][0] = new Token('P', new int[] {1, 0});
		panel[1][1] = new Token('P', new int[] {1, 1});
		panel[1][2] = new Token('P', new int[] {1, 2});
		panel[1][3] = new Token('P', new int[] {1, 3});
		panel[1][4] = new Token('P', new int[] {1, 4});
		panel[1][5] = new Token('P', new int[] {1, 5});
		panel[1][6] = new Token('P', new int[] {1, 6});
		panel[1][7] = new Token('P', new int[] {1, 7});


		//inicializamos negras
		blackKing = new Token('K', new int[] {7, 4});
		panel[7][0] = new Token('T', new int[] {7, 0});
		panel[7][1] = new Token('C', new int[] {7, 1});
		panel[7][2] = new Token('A', new int[] {7, 2});
		panel[7][3] = new Token('Q', new int[] {7, 3});
		panel[7][4] = blackKing;
		panel[7][5] = new Token('A', new int[] {7, 5});
		panel[7][6] = new Token('C', new int[] {7, 6});
		panel[7][7] = new Token('T', new int[] {7, 7});

		panel[6][0] = new Token('P', new int[] {6, 0});
		panel[6][1] = new Token('P', new int[] {6, 1});
		panel[6][2] = new Token('P', new int[] {6, 2});
		panel[6][3] = new Token('P', new int[] {6, 3});
		panel[6][4] = new Token('P', new int[] {6, 4});
		panel[6][5] = new Token('P', new int[] {6, 5});
		panel[6][6] = new Token('P', new int[] {6, 6});
		panel[6][7] = new Token('P', new int[] {6, 7});

		//Autocompletamos lo que queda del tablero

		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {

				panel[i][j] = new Token();
				

			}

		}


	}


	public Token getToken(int[] tokenLocation) {

		return panel[tokenLocation[0]][tokenLocation[1]];
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
			boolean isEnemy = movingToken.isEnemy(targetToken);

			if (isEmpty || (!isEmpty) && (isEnemy)) {
				//comprobamos que el movimiento cumpla el patron de la ficha
				if (isEnemy) {
					movingToken.setCapturing(true);
				}
				if (movingToken.checkTokenPattern(targetLocation)) {
					result = true;
				}				
			} else {
				//si el objetivo es un aliado, comprobamos que sean torre y rey
				switch (Character.toUpperCase(movingToken.getType())) {
				case 'K':
					if (Character.toUpperCase(targetToken.getType()) == 'T') {
						if (targetToken.getCanCast() && movingToken.getCanCast()) {
							result = true;
						}
					}
					break;

				case 'T':
					if (Character.toUpperCase(targetToken.getType()) == 'K') {
						if (targetToken.getCanCast() && movingToken.getCanCast()) {
							result = true;
						}
					}
					break;
				}
			}
		}

		movingToken.setCapturing(false);

		return result;
	}

	private void makeMove(int[] initLocation, Token targetToken) {
		Token movingToken = getToken(initLocation);
		boolean isEmpty = targetToken.getType() == 'e';

		if(isEmpty) {
			movingToken.move(this, targetToken);
		} else {
			//si es enemigo
			if (movingToken.isEnemy(targetToken)) {
				captureToken(targetToken);
				movingToken.move(this, targetToken);
			} else {
				//si es aliado, es un enroque, comprobamos cual es el rey, ya que solo el rey llama a la 'castilng'
				if (Character.toUpperCase(targetToken.getType()) == 'T') {
					movingToken.castling(this, targetToken);
				} else {
					targetToken.castling(this, movingToken);
				}
			}
		}
	}


	boolean isOffBoard(int[] location) {

		return location[0] > 7 || location[0] < 0 || location[1] > 7 || location[1] < 0;
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

	public void showBoard() {

		for (int i = 0; i <= 7; i++) {

			for (int j = 0; j <= 7; j++) {
				System.out.print(panel[i][j].getType());			
			}
			System.out.println();
		}
		

	}
	public boolean kingCapture() {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				if(panel[i][j].getType()=='k'||panel[i][j].getType()=='K') {
					count++;
				}
			}
			if(count == 2) {
				return true;
			}
			
		}
		return false;
		
	}


	/*
	 * Getters y Setters
	 */

	public Token[][] getPanel() {
		return panel;
	}

	public void setPanel(Token[][] panel) {
		this.panel = panel;
	}

	public Token getWhiteKing() {
		return whiteKing;
	}

	public void setWhiteKing(Token whiteKing) {
		this.whiteKing = whiteKing;
	}

	public int getWhiteCapturedTokens() {
		return whiteCapturedTokens;
	}

	public void setWhiteCapturedTokens(int whiteCapturedTokens) {
		this.whiteCapturedTokens = whiteCapturedTokens;
	}

	public int getWhiteScore() {
		return whiteScore;
	}

	public void setWhiteScore(int whiteScore) {
		this.whiteScore = whiteScore;
	}

	public Token[] getWhitePrison() {
		return whitePrison;
	}

	public void setWhitePrison(Token[] whitePrison) {
		this.whitePrison = whitePrison;
	}

	public Token getBlackKing() {
		return blackKing;
	}

	public void setBlackKing(Token blackKing) {
		this.blackKing = blackKing;
	}

	public int getBlackCapturedTokens() {
		return blackCapturedTokens;
	}

	public void setBlackCapturedTokens(int blackCapturedTokens) {
		this.blackCapturedTokens = blackCapturedTokens;
	}

	public int getBlackScore() {
		return blackScore;
	}

	public void setBlackScore(int blackScore) {
		this.blackScore = blackScore;
	}

	public Token[] getBlackPrison() {
		return blackPrison;
	}

	public void setBlackPrison(Token[] blackPrison) {
		this.blackPrison = blackPrison;
	}
	

}
