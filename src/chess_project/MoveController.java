package chess_project;

public class MoveController {

	//variables
	char tokenType;
	
	public MoveController(Token token) {
		this.tokenType = token.getType();
	}
	
	public boolean checkMove(int[] initLocation, int[] targetLocation) {
		boolean result = false;
		//booleana que comprueba que el lugar al que se quiere mover est� en la tabla
		boolean offBoard = (targetLocation[0] > 7 || targetLocation[0] < 0 || targetLocation[1] > 7 || targetLocation[1] < 0);
		
		if(!offBoard) {
			if (checkTokenPattern()) {
				
			}
		}
		
		return result;
	}

	//funci�n que comprueba si el movimiento est� dentro del patr�n de movimiento de la pieza en cuesti�n
	private boolean checkTokenPattern() {
		
		switch (tokenType) {
		case 'p':
		case 'P':
			
			break;

		case 'a':
		case 'A':
			
			break;

		case 'c':
		case 'C':
			
			break;

		case 't':
		case 'T':
			
			break;

		case 'q':
		case 'Q':
			
			break;
		
		case 'k':
		case 'K':
			
			break;

		default:
			break;
		}
		
		return false;
	}
	
}
