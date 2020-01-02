package Interpreter;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

public class Cursor {

	public double x;
	public double y;
	
	public double originX = 0;
	public double originY = 0;
	
	public List<Shape> lines = new LinkedList<>();
	public double lineWidth = 1;
	public double lineHeight = 10;
	
	public AffineTransform af = new AffineTransform();
	
	public double rotation;
	
	public Rectangle2D boundingRect = null;
	
	public Cursor() {}
	
	public void move(double x, double y) {
		af.translate(x, y);
	}
	
	public void rotate(double rotation) {
		af.rotate(rotation);
	}
	
	public void drawLine(double length) {
		Rectangle2D rect = new Rectangle2D.Double(x,y-(lineWidth/2), length, lineWidth);
		lines.add(af.createTransformedShape(new Line2D.Double(0,0,length,0)));
		
		
	}
	
	public void drawLine(double x, double y, double length) {
		Rectangle2D rect = new Rectangle2D.Double(x,y-(lineWidth/2), length, lineWidth);
		Shape transformed = af.createTransformedShape(rect);
		updateBoundingRect(transformed);
		
		lines.add(transformed);
	}
	
	private void updateBoundingRect(Shape shape) {
		if (boundingRect == null) boundingRect = shape.getBounds2D();
		else boundingRect = boundingRect.createUnion(shape.getBounds2D());
	}
	
}
