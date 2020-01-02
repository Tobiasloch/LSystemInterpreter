package Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Plus extends Instruction {

	public Plus() {
		super(Plus.getDefaultRepresentation());
	}
	public Plus(double angle) {
		super(Plus.getDefaultRepresentation());
		this.angle = angle;
	}

	public static double defaultAngle = Math.toRadians(20);
	public double angle = defaultAngle;
	
	@Override
	public Cursor paintInstruction(Cursor cursor, int depth, int maxDepth) {
		cursor.rotate(-angle);
		return cursor;
	}
	
	public static String getDefaultRepresentation() {
		return "+";
	}

	public static String getAlphabet() {
		return "\\+(?:\\((\\d+)\\))?";
	}
	
	public static Plus create(String representation) {
		Matcher m = Pattern.compile(Plus.getAlphabet()).matcher(representation);
		if (m.matches()) {
			Plus p = null;
			if (m.group(1) != null) {
				p = new Plus(Math.toRadians(Double.parseDouble(m.group(1))));
			} else {
				p = new Plus();
			}
			return p;
		} else return null;
	}

}
