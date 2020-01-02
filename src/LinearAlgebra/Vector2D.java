package LinearAlgebra;

public class Vector2D {
	
	public double x;
	public double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D multiply(double scalar) {
		return new Vector2D(scalar*x, scalar*y);
	}
	
	public double norm() {
		return Math.sqrt(x*x+(y*y));
	}
	
	public Vector2D normalise() {
		return multiply(1/norm());
	}
	
	public Vector2D perpendicular() {
		return new Vector2D(y, -x);
	}
	
	public Vector2D add(Vector2D vect) {
		return new Vector2D(x + vect.x, y + vect.y);
	}
	
	public Vector2D subtract(Vector2D vect) {
		return new Vector2D(x - vect.x, y - vect.y);
	}
}
