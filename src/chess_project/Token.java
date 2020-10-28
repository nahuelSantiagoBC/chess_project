package chess_project;

public class Token {

	//propiedades de la ficha
	boolean isCaptured;
	String color;
	char type;
	
	//construstores
	Token(boolean isCaptured, String color, char type){
		
		this.isCaptured = isCaptured;
		this.color = color;
		this.type = type;
	}

	public boolean isCaptured() {
		return isCaptured;
	}

	public String getColor() {
		return color;
	}

	public char getType() {
		return type;
	}

	public void setMuerta(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setTipo(char type) {
		this.type = type;
	}
	
}
