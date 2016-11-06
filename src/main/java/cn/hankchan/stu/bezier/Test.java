package cn.hankchan.stu.bezier;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

/*import javafx.scene.paint.Paint;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
*/
public class Test {

	@org.junit.Test
	public void testGetCtrPointF() {
		List<PointFV1d0> lists = new ArrayList<>(); 
		lists.add(new PointFV1d0(0, 0));
		lists.add(new PointFV1d0(20, 10));
		lists.add(new PointFV1d0(30, 40));
		lists.add(new PointFV1d0(60, 20));
		lists.add(new PointFV1d0(80, 60));
		List<PointFV1d0> ctrPointF =  Bezier.getCtrlPointF(lists, 0, 0);
		List<PointFV1d0> nodeAndCtrls = Bezier.getNodesAndCtrlsPointF(lists, 0, 0);
		List<PointFV1d0> withOutCtrls = Bezier.getBeziersPointsWithoutCtrl(nodeAndCtrls);
		for(int i = 0; i < 13; i++) {
			System.out.println(nodeAndCtrls.get(i).getX() + "==" + nodeAndCtrls.get(i).getY() + "|||||||||||" + withOutCtrls.get(i).getX() + "==" + withOutCtrls.get(i).getY());
		}
//		System.out.println(nodeAndCtrls + "---------------" + withOutCtrls);
		for(PointFV1d0 p : ctrPointF) {
			PointFV1d0 list = new PointFV1d0();
			list = p;
			lists.add(list);
//			System.out.println(p.getX() + "===>" + p.getY());
		}
//		System.out.println("----------------------------lists 's size = " + lists.size());
		for(PointFV1d0 p : lists) {
//			System.out.println(p.getX() + "===>" + p.getY());
		}
		/* 绘图 */
		for(int i = 0; i < lists.size() - ctrPointF.size() - 1; i++) {
			List<PointFV1d0> pfs = Bezier.getBezierPointFs(lists.get(i), lists.get(i + 5), lists.get(i + 6), lists.get(i + 1));
			Bezier.turnToIntPoints(1024, 512, 50f, 150f, 8f, 50f, pfs);
			BufferedImage image = new BufferedImage(1024, 648, BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D g = (Graphics2D) image.getGraphics();
			
		}
		
		/*
		 * 待后测
		for(int i = 0; i < lists.size() - 1; i++) {
			List<PointFV1d0> result = Bezier.getBezierPointFs(lists.get(i), ctrPointF.get(i), ctrPointF.get(i + 1), lists.get(i + 1));
			List<PointV1d0> points = Bezier.turnToIntPoints(1024, 640, 50f, 150f, 8f, 50f, result);
			BufferedImage image = new BufferedImage(1024, 512, BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D g = (Graphics2D) image.getGraphics();
			
			 for(int j = 0; j < lists.size() - 1; j++) {
				 int x1 = points.get(j).getX();
				 int y1 = points.get(j).getY();
				 int x2 = points.get(j + 1).getX();
				 int y2 = points.get(j + 1).getY();
				 g.setColor(new Color(132, 255, 24));
				 g.setBackground(new Color(0, 0, 255));
			     BasicStroke stroke = new BasicStroke(1);
			     g.setStroke(stroke);
				 g.drawLine(x1, y1, x2, y2);
			 }
			 try {
				ImageIO.write(image, "png", new File("123456.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/
	}
	
/*	@org.junit.Test
	public void testQuadCurve() {
		QuadCurve quadCurve = null;
		quadCurve = new QuadCurve();
		double startX = 60, startY = 10, endX = 130, endY = 15, ctr1X = 78, ctr1Y = 45, ctr2X = 126, ctr2Y = 32;
		quadCurve.setStartX(startX);
		quadCurve.setStartY(startY);
		quadCurve.setEndX(endX);
		quadCurve.setEndY(endY);
		quadCurve.setControlX(ctr1X);
		quadCurve.setControlY(ctr1Y);
		

		CubicCurve cubicCurve = null;
		cubicCurve = new CubicCurve(startX, startY, ctr1X, ctr1Y, ctr2X, ctr2Y, endX, endY);
		
		
		
		BufferedImage image = new BufferedImage(1024, 648, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(new Color(132, 255, 24));
		g.setBackground(new Color(0, 0, 255));
	    BasicStroke stroke = new BasicStroke(5);
	    g.setStroke(stroke);
		try {
			ImageIO.write(image, "png", new File("quad_curve_bezier.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
//	@org.junit.Test
//	public void test2() {
//		PointFV1d0 p1 = new PointFV1d0(50, 60);
//		PointFV1d0 c1 = new PointFV1d0(130, 200);
//		PointFV1d0 c2 = new PointFV1d0(300, 360);
//		PointFV1d0 p2 = new PointFV1d0(400, 600);
//		List<PointV1d0> lists = Bezier.turnToIntPoints(1024, 648, 108f, 120f, 19f, 22f, 
//				Bezier.getBezierPointFs(p1, c1, c2, p2));
//		BufferedImage image = new BufferedImage(1024, 648, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = (Graphics2D) image.getGraphics();
//	}
	
	@org.junit.Test
	public void test() {
		//输入点
		try {
			PointFV1d0 p1 = new PointFV1d0(60, 10);
			PointFV1d0 c1 = new PointFV1d0(78, 45);
			PointFV1d0 c2 = new PointFV1d0(126, 32);
			PointFV1d0 p2 = new PointFV1d0(130, 15);
			//获取绘制的点的坐标
			List<PointFV1d0> result = Bezier.getBezierPointFs(p1, c1, c2, p2);
			for(PointFV1d0 p : result) {
				System.out.println(p.getX() + "===>" + p.getY());
			}
			//将坐标转化为图片上的坐标
			List<PointV1d0> lists = Bezier.turnToIntPoints(1024, 512, 50f, 150f, 8f, 50f, result);
			BufferedImage image = new BufferedImage(1024, 648, BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D g = (Graphics2D) image.getGraphics();
			
			 for(int j = 0; j < lists.size() - 1; j++) {
				 int x1 = lists.get(j).getX();
				 int y1 = lists.get(j).getY();
				 int x2 = lists.get(j + 1).getX();
				 int y2 = lists.get(j + 1).getY();
				 g.setColor(new Color(132, 255, 24));
				 g.setBackground(new Color(0, 0, 255));
			     BasicStroke stroke = new BasicStroke(5);
			     g.setStroke(stroke);
				 g.drawLine(x1, y1, x2, y2);
			 }
			 ImageIO.write(image, "png", new File("testBezier.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
