package Interpreter;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Line extends Line2D{
	
	public double x1;
	public double y1;
	public double x2;
	public double y2;
	
	public double width = 1;
	
	public Line(double x1,double y1,double x2,double y2) {
		setLine(x1, y1, x2, y2);
		Rectangle r = super.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle.Double(Math.min(x1, x2), Math.min(y1, y2)
				,Math.max(x2-x1, x1-x2), Math.max(y2-y1, y1-y2));
	}

	@Override
	public Point2D getP1() {
		return new Point.Double(x1, y1);
	}

	@Override
	public Point2D getP2() {
		return new Point.Double(x2,y2);
	}

	@Override
	public double getX1() {
		return x1;
	}

	@Override
	public double getX2() {
		return x2;
	}

	@Override
	public double getY1() {
		return y1;
	}

	@Override
	public double getY2() {
		return y2;
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

}
