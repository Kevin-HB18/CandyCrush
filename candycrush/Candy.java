package candycrush;

public class Candy {
	private String dulce;
	private String color;
	
	// Constructor
	
	Candy (String dulce, String color) {
		this.dulce = dulce;
		this.color = color;
	}

	// Getters y Setters
	
	public String getDulce() {
		return dulce;
	}

	public void setDulce(String dulce) {
		this.dulce = dulce;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
