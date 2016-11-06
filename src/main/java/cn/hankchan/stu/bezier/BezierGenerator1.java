package cn.hankchan.stu.bezier;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position.Bias;
import javax.swing.text.View;

@Deprecated
public class BezierGenerator1 extends View {

	public BezierGenerator1(Element elem) {
		super(elem);
		init();
	}

	private void init() {
		
	}
	
	@Override
	public float getPreferredSpan(int axis) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paint(Graphics g, Shape allocation) {
		// TODO Auto-generated method stub
		g.setColor(new Color(255, 255, 255));
		g.setClip(allocation);
		
	}

	@Override
	public Shape modelToView(int pos, Shape a, Bias b) throws BadLocationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int viewToModel(float x, float y, Shape a, Bias[] biasReturn) {
		// TODO Auto-generated method stub
		return 0;
	}

}
