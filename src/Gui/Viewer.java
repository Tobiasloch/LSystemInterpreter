package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Interpreter.Cursor;
import Interpreter.Formula;
import Interpreter.Line;
import Interpreter.Minus;
import Interpreter.Plus;
import Interpreter.Variable;

public class Viewer extends javax.swing.JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1991711259545854689L;

	public Variable axiom;
	
	public SettingsBar settingsBar;
	
	public int depth = 1;
	
	public double scale = 1;
	public double scaleSpeed = (double)1/10;
	public double originX;
	public double originY;
	
	public double rotation = 0;
	
	public Viewer(Variable formula) {
		super();
		
		this.axiom = formula;
		
		initialize();
	}
	
	public void initialize() {
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				scale += scaleSpeed * e.getWheelRotation();
				repaint();
			}
		});
	}
	
	public void view(String formulas, String axiom) {
		depth = Integer.parseInt(settingsBar.depthTextField.getText());
		Plus.defaultAngle = Math.toRadians(Double.parseDouble(settingsBar.plusAngleTextField.getText()));
		Minus.defaultAngle = Math.toRadians(Double.parseDouble(settingsBar.minusAngleTextfield.getText()));
		rotation = settingsBar.getRotation();
		
		HashMap<String, Variable> map = Formula.parse(formulas);
		
		this.axiom = map.get(axiom);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		//g2.scale(scale, scale);
		
		Cursor c = axiom.paintInstruction(depth);
		Rectangle2D rect = c.boundingRect;
		
		if (rect != null) {
			g2.scale(getWidth()/rect.getWidth(), getHeight()/rect.getHeight());
			g2.translate(-rect.getX(), -rect.getY());
			
			g2.rotate(rotation, rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
		}
		
		g2.setColor(Color.BLACK);
		for (Shape l : c.lines) {
			g2.fill(l);
		}
	}
	
	
	public static void main(String[] args) {
		HashMap<String, Variable> map;
		map = Formula.parse("F->F+F--F+F");
		//map = Formula.parse("F -> FF\n" + 
		//		"X -> F-[X]+X+F[+FX]-X");
		//map = Formula.parse("F -> F[+F]-F");
		//map = Formula.parse("F -> FF +[+F -F -F -F]-[-F +F +F]");
		
		Viewer v = new Viewer(map.get("F"));
		
		JFrame frame = new JFrame("TestWindow");
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new JScrollPane(v), BorderLayout.CENTER);
		v.settingsBar = new SettingsBar(v);
		frame.add(new FormulaInputBar(v), BorderLayout.SOUTH);
		frame.add(v.settingsBar, BorderLayout.WEST);

		frame.setVisible(true);
	}
}
