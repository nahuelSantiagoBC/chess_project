package chess_project;

public class Token {

	//propiedades de la ficha
	private boolean isCapturing;
	private int value;
	private char type;
	private int[] currentLocation;
	private boolean canCast;

	//construstores
	public Token(char type, int[] currentLocation){

		this.isCapturing = false;
		this.type = type;
		value = calculateValue();
		this.currentLocation = currentLocation;
		if (Character.toUpperCase(type) == 'T' || Character.toUpperCase(type) == 'K') {
			canCast = true;
		} else {
			canCast = false;
		}
	}


	public Token() {
		type = 'e';
	}


	//funciones
	private int calculateValue() {
		int result;

		switch (Character.toLowerCase(type)) {
		case 'p':
			result = 1;
			break;

		case 'a':
		case 'c':
			result = 3;
			break;

		case 't':
			result = 5;
			break;

		case 'q':
			result = 9;
			break;

		case 'k':
			result = 0;
			break;

		default:
			result = 0;
			break;
		}


		return result;
	}

	public boolean checkTokenPattern(int[] targetLocation) {
		boolean result = false;
		boolean movedOneUp;
		boolean movedOneDown;
		boolean movedOneRight;
		boolean movedOneLeft;
		boolean movedDiagonally;
		boolean movedHorizontally;
		boolean movedVertically;


		switch (Character.toLowerCase(type)) {
		case 'p':

			movedOneUp = (targetLocation[1] == currentLocation[1]+1) && type == 'P';
			movedOneDown = (targetLocation[1] == currentLocation[1]-1) && type == 'p';

			if (isCapturing) {
				boolean movedOneDiagonally = (targetLocation[0] - currentLocation[0] == targetLocation[1] - currentLocation[1]) && targetLocation[0] - currentLocation[0] == Math.abs(1);

				//comprobamos si se ha movido en diagonal 1 puesto
				result = (movedOneDiagonally) && ( movedOneUp || movedOneDown);
			} else {
				//comprobamos si está en la segunda o penúltima línea
				if (currentLocation[1] == 1 || currentLocation[1] == 6) {
					boolean movedTwoUp = (targetLocation[1] == currentLocation[1]+2) && type == 'P';
					boolean movedTwoDown = (targetLocation[1] == currentLocation[1]-2) && type == 'p';

					result = movedOneDown || movedOneUp || movedTwoDown || movedTwoUp;
				} else {
					result = movedOneDown || movedOneUp;
				}
			}

			break;

		case 'a':

			movedDiagonally = targetLocation[0] - currentLocation[0] == targetLocation[1] - currentLocation[1];

			result =movedDiagonally;

			break;

		case 'c':

			boolean jumpedUpLeft = (targetLocation[0] == currentLocation[0] - 1 && targetLocation[1] == currentLocation[1] + 2);
			boolean jumpedLeftUp = (targetLocation[0] == currentLocation[0] - 2 && targetLocation[1] == currentLocation[1] + 1);
			boolean jumpedUpRight = (targetLocation[0] == currentLocation[0] + 1 && targetLocation[1] == currentLocation[1] + 2);
			boolean jumpedRightUp = (targetLocation[0] == currentLocation[0] + 2 && targetLocation[1] == currentLocation[1] + 1);
			boolean jumpedDownLeft = (targetLocation[0] == currentLocation[0] + 1 && targetLocation[1] == currentLocation[1] - 2);
			boolean jumpedLeftDown = (targetLocation[0] == currentLocation[0] + 2 && targetLocation[1] == currentLocation[1] - 1);
			boolean jumpedDownRight = (targetLocation[0] == currentLocation[0] - 1 && targetLocation[1] == currentLocation[1] - 2);
			boolean jumpedRightDown = (targetLocation[0] == currentLocation[0] - 2 && targetLocation[1] == currentLocation[1] - 1);

			result = jumpedDownLeft || jumpedDownRight || jumpedLeftDown || jumpedLeftUp  || jumpedRightDown || jumpedRightUp || jumpedUpLeft || jumpedUpRight;

			break;

		case 't':

			movedHorizontally = (targetLocation[0] != currentLocation[0]) && (targetLocation[1] == currentLocation[1]);
			movedVertically = (targetLocation[0] == currentLocation[0]) && (targetLocation[1] != currentLocation[1]);

			result = movedVertically || movedHorizontally;

			break;

		case 'q':

			movedDiagonally = targetLocation[0] - currentLocation[0] == targetLocation[1] - currentLocation[1];
			movedHorizontally = (targetLocation[0] != currentLocation[0]) && (targetLocation[1] == currentLocation[1]);
			movedVertically = (targetLocation[0] == currentLocation[0]) && (targetLocation[1] != currentLocation[1]);

			result = movedDiagonally || movedHorizontally || movedVertically;

			break;

		case 'k':

			movedOneUp = (targetLocation[1] == currentLocation[1]+1) && type == 'P';
			movedOneDown = (targetLocation[1] == currentLocation[1]-1) && type == 'p';
			movedOneRight = (targetLocation[0] == currentLocation[0]+1);
			movedOneLeft = (targetLocation[0] == currentLocation[0]-1);

			result = movedOneDown || movedOneLeft || movedOneRight || movedOneUp;

			break;
		}

		return result;
	}

	public void move(Board board, Token targetToken) {
		int[] targetLocation = targetToken.getCurrentLocation();
		Token[][] updatedPanel = board.getPanel();
		updatedPanel[targetLocation[0]][targetLocation[1]] = this;
		updatedPanel[currentLocation[0]][currentLocation[1]] = new Token();
		board.setPanel(updatedPanel);
		setCurrentLocation(targetLocation);
		this.canCast = false;
	}

	public void castling(Board board, Token rook) {
		int[] rookLocation = rook.getCurrentLocation();
		rook.move(board, this);
		this.move(board, board.getToken(rookLocation));
		this.canCast = false;
		rook.canCast = false;

	}

	public boolean isEnemy(Token token) {
		return Character.isUpperCase(token.getType()) != Character.isUpperCase(this.getType());
	}

	
	//getters y setters

	public boolean isCapturing() {
		return isCapturing;
	}

	public char getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	public void setCurrentLocation(int[] currentLocation){
		this.currentLocation = currentLocation; 
	}

	public int[] getCurrentLocation() {
		return currentLocation;
	}

	public void setCapturing(boolean isCapturing) {
		this.isCapturing = isCapturing;
	}

	public void setType(char type) {
		this.type = type;
	}

	public boolean getCanCast() {
		return canCast;
	}

	public void setCanCast(boolean canCast) {
		this.canCast = canCast;
	}

}
