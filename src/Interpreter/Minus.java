package Interpreter;

import java.awt.Graphics2D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Minus extends Instruction {

	public Minus() {
		super(Minus.getDefaultRepresentation());
	}
	
	public Minus(double angle) {
		super(Minus.getDefaultRepresentation());
		this.angle = angle;
	}

	public static double defaultAngle = Math.toRadians(20);
	public double angle = defaultAngle;
	
	@Override
	public Cursor paintInstruction(Cursor cursor, int depth, int maxDepth) {
		cursor.rotate(angle);
		return cursor;
	}
	
	public static String getDefaultRepresentation() {
		return "-";
	}

	public static String getAlphabet() {
		return "-(?:\\((\\d+)\\))?";
	}
	
	public static Minus create(String representation) {
		Matcher m = Pattern.compile(Minus.getAlphabet()).matcher(representation);
		if (m.matches()) {
			Minus min = null;
			if (m.group(1) != null) {
				min = new Minus(Math.toRadians(Double.parseDouble(m.group(1))));
			} else {
				min = new Minus();
			}
			return min;
		} else return null;
	}
}
