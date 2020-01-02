package Interpreter;

import java.util.HashSet;

public abstract class Instruction {
	
	public String representation;
	
	public abstract Cursor paintInstruction(Cursor cursor, int depth, int maxDepth);
	
	public Cursor paintInstruction(int maxDepth) {
		return paintInstruction(new Cursor(), 0, maxDepth);
	}
	
	public static String getAlphabet() {
		return Variable.getAlphabet() + "|" + Plus.getAlphabet() + "|" + Minus.getAlphabet() + "|"
				+ MovementWithoutPainting.getAlphabet() + "|" + Branch.getAlphabet();
	}
	
	public Instruction(String representation) {
		this.representation = representation;
	}
	
	public static Instruction create(String instruction) {
		Instruction inst;
		
		inst = Variable.create(instruction);
		if (inst != null) return inst;
		
		inst = Plus.create(instruction);
		if (inst != null) return inst;
		
		inst = Minus.create(instruction);
		if (inst != null) return inst;
		
		inst = MovementWithoutPainting.create(instruction);
		if (inst != null) return inst;
		
		inst = Branch.create(instruction);
		return inst;
	}

}
