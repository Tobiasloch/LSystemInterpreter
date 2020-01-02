package Interpreter;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable extends Instruction {
	
	public static int defaultLength = 10;
	public int length = defaultLength;
	
	public Variable(String representation) {
		super(representation);
	}

	public Formula formula;

	@Override
	public Cursor paintInstruction(Cursor cursor, int depth, int maxDepth) {
		if (depth >= maxDepth) {
			cursor.drawLine(length);
			cursor.move(length, 0);
		} else {
			formula.paintInstruction(cursor, depth+1, maxDepth);
		}
		
		return cursor;
	}

	public static String getDefaultRepresentation() {
		return "F";
	}

	public static String getAlphabet() {
		return "[A-Z]";
	}
	
	public String generateRepresentation(boolean subVariables) {
		String representation = "";
		
		Iterator it = formula.variables.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Variable v = (Variable) pair.getValue();
	        
	        if (!subVariables && !v.representation.equals(this.representation)) continue;
	        
	        representation += v.representation + " -> " + v.formula.generateRepresentation() + "\n";
	    }
	    
	    return representation;
	}
	
	public static Variable create(String representation) {
		Matcher m = Pattern.compile(Variable.getAlphabet()).matcher(representation);
		if (m.matches()) {
			return new Variable(representation);
		} else return null;
	}

}
