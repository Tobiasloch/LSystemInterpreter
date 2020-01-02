package Interpreter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula extends Instruction {
	
	public Formula(String representation) {
		super(representation);
	}

	public List<Instruction> instructions = new LinkedList<>();
	public HashMap<String, Variable> variables = new HashMap<>();

	@Override
	public Cursor paintInstruction(Cursor cursor, int depth, int maxDepth) {
		for (Instruction inst : instructions) {
			inst.paintInstruction(cursor, depth, maxDepth);
		}
		
		return cursor;
	}

	public static String getDefaultRepresentation() {
		return "F->F";
	}

	public static String getAlphabet() {
		return "[ \\t]*(" + Variable.getAlphabet() + ")" + "[ \\t]*->[ \\t]*" + "((?:(?:" + Instruction.getAlphabet() + ")[ \\\\t]*)+)[ \\t]*";
	}
	
	public static HashMap<String, Variable> parse(String formulas) {
		HashMap<String, Variable> varMap = new HashMap<>();
		
		for (String f : formulas.split("[\\n\\r\\f]")) {
			Stack<Variable> branchStack = new Stack<>();
			Matcher fMatcher = Pattern.compile(Formula.getAlphabet()).matcher(f);
			if (!fMatcher.matches()) continue;
			

			HashMap<String, Variable> internalVarMap = new HashMap<>();
			String formulaName = fMatcher.group(1);
			String formula = fMatcher.group(2);
			
			Matcher m = Pattern.compile(Instruction.getAlphabet()).matcher(formula);
			Variable v = null;
			
			if (varMap.containsKey(formulaName)) {
				v = varMap.get(formulaName);
			} else {
				v = new Variable(formulaName);
				varMap.put(formulaName, v);
			}
			
			v.formula = new Formula(f);
			branchStack.push(v);
			
			while(m.find()) {
				String token = m.group(0);
				
				Instruction i = Instruction.create(token);
				
				if (i == null) {
					throw new IllegalStateException("token " + token + " is null but got matched!");
				}
				
				 if (i instanceof Branch) {
					Branch b = (Branch) i;
					b.formula = new Formula("");

					if (b.isOpenBracket()) {
						branchStack.peek().formula.instructions.add(i);
						branchStack.push((Branch) i);
					}
					else {
						branchStack.pop();
					}
					continue;
				} else if (i.getClass().equals(Variable.class)) {
					if (varMap.containsKey(i.representation)) {
						i = varMap.get(i.representation);
					} else {
						varMap.put(i.representation, (Variable) i);
					}
					
					internalVarMap.put(i.representation, (Variable)i);
				}
				
				branchStack.peek().formula.instructions.add(i);
			}
			
			v.formula.variables = internalVarMap;
		}
		
		Iterator it = varMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Variable v = (Variable) pair.getValue();
	        if (v.formula == null) {
	        	throw new IllegalStateException("A Variable is used but not defined!");
	        }
	    }
		
		return varMap;
	}
	
	
	public String generateRepresentation() {
		String rep = "";
		for (Instruction i : instructions) {
			if (i instanceof Branch) {
				rep += ((Branch) i).generateRepresentation();
				
			}else rep += i.representation;
		}
		
		return rep;
	}
}
