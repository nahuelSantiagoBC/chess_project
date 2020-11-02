package chess_project;

public class Token {

	//propiedades de la ficha
	boolean isCapturing;
	int value;
	char type;
	int[] currentLocation;
	
	//construstores
	public Token(char type){
		
		this.isCapturing = false;
		this.type = type;
		value = calculateValue();
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
					movedOneRight = (targetLocation[0] == currentLocation[0]+1);
					movedOneLeft = (targetLocation[0] == currentLocation[0]-1);
					
					//comprobamos si se ha movido en diagonal 1 puesto
					result = (movedOneDown && movedOneRight) || (movedOneDown && movedOneLeft) || (movedOneUp && movedOneRight) || (movedOneUp && movedOneLeft);
				} else {
					//comprobamos si está en la segunda o penúltima línea
					if (currentLocation[1] == 1 || currentLocation[1] == 7) {
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
				
				boolean jumpedUpLeft = (targetLocation[0] == currentLocation[0] + 1 && targetLocation[1] == currentLocation[1] + 2);
				boolean jumpedLeftUp = (targetLocation[0] == currentLocation[0] + 2 && targetLocation[1] == currentLocation[1] + 1);
				boolean jumpedUpRight = (targetLocation[0] == currentLocation[0] - 1 && targetLocation[1] == currentLocation[1] + 2);
				boolean jumpedRightUp = (targetLocation[0] == currentLocation[0] - 2 && targetLocation[1] == currentLocation[1] + 1);
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
	
			default:
				break;
		}
		
		return result;
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
	
	public void setCapturing(boolean isCapturing) {
		this.isCapturing = isCapturing;
	}

	public void setType(char type) {
		this.type = type;
	}
	
}
