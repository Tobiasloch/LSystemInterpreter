package Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovementWithoutPainting extends Variable {

	public MovementWithoutPainting(String representation) {
		super(representation);
	}

	@Override
	public Cursor paintInstruction(Cursor cursor, int depth, int maxDepth) {
		cursor.move(super.length, 0);
		return cursor;
	}

	public static String getDefaultRepresentation() {
		return "f";
	}

	public static String getAlphabet() {
		return "f";
	}
	
	public static MovementWithoutPainting create(String representation) {
		Matcher m = Pattern.compile(MovementWithoutPainting.getAlphabet()).matcher(representation);
		if (m.matches()) {
			return new MovementWithoutPainting(representation);
		} else return null;
	}

}
