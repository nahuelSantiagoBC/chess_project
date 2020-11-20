package chess_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;




public class ChessTester {

	private static Scanner inputReader;
	
	public static void main(String[] args) {
		
		Board board = new Board();		//Inicializamos el tablero
		
		inputReader = new Scanner(System.in);
		//Bucle del juego que no acaba hasta que no se se capture a un rey
		play(board);
		inputReader.close();
		
	}

	//función que inicia la partida
	private static void play(Board board) {
		
		boolean endGame = false;
		StringBuffer player = new StringBuffer();
		
		//iniciamos el turno hasta que termine la partida
		do {
			//seteamos el valor player que toca
			if (player.toString().equals("WHITE")) {
				player.replace(0, player.length(), "black");
			} else {
				player.replace(0, player.length(), "WHITE");
			}
			
			endGame = startTurn(board, player.toString());

		}while(!endGame);
		
	}

	//función que realiza el turno de un jugador pasado por parámetro
	private static boolean startTurn(Board board, String player) {
		
		boolean endGame = false;
		boolean turnEnd = false;
		int[] movingTokenLocation;
		int[] targetTokenLocation;
		
		
		System.out.println(toCamelCase(player) + " turn!");
		do {
			movingTokenLocation = getMovingToken(board, player);
			targetTokenLocation = getTargetToken(board, movingTokenLocation);
			board.makeMove(movingTokenLocation, targetTokenLocation);
		} while (!turnEnd);
		
		if (board.isKingCaptured()) {
			endGame = true;
			System.out.println(toCamelCase(player) + " team WINS!");
		}
		
		return endGame;
	}
	
	


	private static int[] getMovingToken(Board board, String player) {
		boolean validLocation = false;
		int[] location;
		
		board.showBoard();
		System.out.println("Select a " + player.toLowerCase() + " token to move:");
		do {
			location = requestChessBoardLocation();
			if (board.checkIfCorrectColor(location, player)) {
				validLocation = true;
			} else {
				System.out.println("That location  has not a " + player.toLowerCase() + " token!");
				board.showBoard();
			}
		} while (!validLocation);
		
		return location;
	}

	private static int[] getTargetToken(Board board, int[] movingTokenLocation) {
		boolean validLocation = false;
		int[] location;
		
		board.showBoard();
		System.out.println("Select a location to move");
		do {
			location = requestChessBoardLocation();
			
			if (board.checkMove(movingTokenLocation, location)) {
				validLocation = true;
			} else {
				System.out.println("Location not valid!");
				board.showBoard();
			}
		} while(!validLocation);
		
		return location;
	}
	
	private static int[] requestChessBoardLocation() {
		
		int[] result = {0 , 0};
		
		result[1] = requestXCoordinateAsLetter();
		result[0] = requestYCoordinate();
		
		return result;
	}

	private static int requestXCoordinateAsLetter() {

		ArrayList<Character> validLetters = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'));

		boolean validValue = false;
		char input = ' ';
		int result = -1;
		String messageForUser = "Type the location's letter (A-H):";
		String errorMessage = "That's not a single character! Please type one character and press enter. Not more nor less.";
		
		do {
			input = requestSingleCharacter(messageForUser, errorMessage);
			validValue = validLetters.contains(input);
			if (validValue) {
				result = validLetters.indexOf(input);
			} else {
				System.out.println("That's not a valid letter.");				
			}
		} while (!validValue);
		
		return result;
	}

	private static char requestSingleCharacter(String messageForUser, String notSingleCharMessage) {
		
		String input;
		char result = 0;
		boolean isSingleCharacter = false;
		
		do {
			System.out.println(messageForUser);
			input = inputReader.next().trim();
			if (input.length() == 1) {
				result = Character.toUpperCase(input.charAt(0));
				isSingleCharacter = true;
			} else {
				System.out.println(notSingleCharMessage);
			}
		} while (!isSingleCharacter);
		
		return result;
	}

	private static int requestYCoordinate() {

		ArrayList<Integer> validNumbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

		boolean validValue = false;
		int result = -1;
		String messageForUser = "Type the location's number (1-8):";
		String errorMessage = "That's not an integer! Please, type an integer.";
		
		do {
			result = requestSingleInteger(messageForUser, errorMessage);
			validValue = validNumbers.contains(result);
			if (validValue) {
				result = validNumbers.indexOf(result);
			} else {
				System.out.println("That's not a valid integer.");				
			}
		} while (!validValue);
		
		return result;
	}
	
	private static int requestSingleInteger(String messageForUser, String notSingleIntegerMessage) {

		String input;
		int result = -1;
		boolean isSingleInteger = false;
		inputReader = new Scanner(System.in);
		
		do {
			System.out.println(messageForUser);
			input = inputReader.next().trim();
			if (input.length() != 1 || !isInteger(input)) {
				System.out.println(notSingleIntegerMessage);
			} else {
				result = Integer.parseInt(input);
				isSingleInteger = true;
			}
		} while (!isSingleInteger);
		
		return result;
	}

	private static String toCamelCase(String input) {
		
		StringBuffer output;
		
		output = new StringBuffer (input.toLowerCase());
		output.replace(0, 1, "" + Character.toUpperCase(output.charAt(0)));
		
		return output.toString();
	}

	public static boolean isInteger(String string) {
		int counter = 0;
		if (string == null || string.isEmpty()) {
			return false;
		}
		if (string.charAt(0) == '-') {
			if (string.length() > 1) {
				counter++;
			} else {
				return false;
			}
		}
		for (; counter < string.length(); counter++) {
			if (!Character.isDigit(string.charAt(counter))) {
				return false;
			}
		}
		return true;

	}
	public static int numberComp(String locationString, Scanner scan) {
		
		if(!isInteger(locationString)) {
			do {
				System.out.println("choose a row from 1 to 8");
				locationString = scan.nextLine();
			}while(!isInteger(locationString));
		}
		
		return Integer.parseInt(locationString) - 1;
	}
}