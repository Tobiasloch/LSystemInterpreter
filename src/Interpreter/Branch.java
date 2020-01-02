package Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Branch extends Variable {
	
	public Branch(String representation) {
		super(representation);
	}

	@Override
	public Cursor paintInstruction(Cursor cursor, int depth, int maxDepth) {
		double x = cursor.x;
		double y = cursor.y;
		double rotation = cursor.rotation;
		
		formula.paintInstruction(cursor, depth, maxDepth);
		
		cursor.x = x;
		cursor.y = y;
		cursor.rotation = rotation;
		
		return cursor;
	}
	
	public String generateRepresentation() {
		String rep = "[" + formula.generateRepresentation() + "]";
		
		return rep;
	}
	
	public static String getDefaultRepresentation() {
		return "[]";
	}

	public static String getAlphabet() {
		return getOpenAlphabet() + "|" + getCloseAlphabet();
	}
	
	public static String getOpenAlphabet() {
		return "\\[";
	}
	public static String getCloseAlphabet() {
		return "\\]";
	}
	
	public boolean isOpenBracket() {
		Matcher open = Pattern.compile(Branch.getOpenAlphabet()).matcher(representation);
		Matcher close = Pattern.compile(Branch.getCloseAlphabet()).matcher(representation);		
		
		if (open.matches()) return true;
		else if (close.matches()) return false;
		else {
			throw new IllegalStateException("Branch is neither open nor close");
		}
	}

	public static Branch create(String representation) {
		Matcher m = Pattern.compile(Branch.getAlphabet()).matcher(representation);
		
		if (m.matches()) {
			return new Branch(representation);
		} else return null;
	}
}
