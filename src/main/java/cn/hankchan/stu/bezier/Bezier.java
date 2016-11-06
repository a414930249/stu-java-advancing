package cn.hankchan.stu.bezier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Hank_  Email:hicth_chan@163.com
 * @version 创建时间: 18 Jul 201614:44:31
 * <p>类说明: 贝塞尔曲线工具类 ： 只传入需要穿过的点的坐标即可返回需要绘制三阶贝塞尔曲线上的点的路径坐标。<p>
 * <p>先调用 getNodesAndCtrlsPointF(List<PointFV1d0> lists, float a, float b) 方法获取包含目标点坐标及控制点坐标的集合
 * 再以上述方法返回的集合作为参数调用 getBeziersPointsWithoutCtrl(List<PointFV1d0> lists) 方法获取所需要绘制的目标点的路径坐标</p>
 */
public class Bezier {

	/**
	 * 根据给定的包括起止点、控制点坐标的集合，返回这些点转换成3次贝塞尔曲线后该曲线路径上的坐标点的集合
	 * @param lists 输入点集合
	 * @return
	 */
	public static List<PointFV1d0> getBeziersPointsWithoutCtrl(List<PointFV1d0> lists) {
		/**
		 * 每次循环都是从第一个非控制点到第二个非控制点
		 * <p> 即所有的非控制点为: lists[0] lists[3] lists[6] ...... lists[lists.size() - 4] </p>
		 * <p> 所有的控制点为: lists[1] lists[2] lists[4] lists[5] ...... lists[lists.size() - 3] lists[lists.size() - 2] </p>
		 */
		List<PointFV1d0> result = new ArrayList<>();
		for(int i = 0; i < lists.size() - 3; i += 3) {
			PointFV1d0 start = lists.get(i);
			PointFV1d0 end = lists.get(i + 3);
			PointFV1d0 ctrl1 = lists.get(i + 1);
			PointFV1d0 ctrl2 = lists.get(i + 2);
			List<PointFV1d0> list = getBezierPointFs(start, ctrl1, ctrl2, end);
			for(PointFV1d0 p : list) {
				PointFV1d0 p0 = new PointFV1d0();
				p0 = p;
				result.add(p0);
			}
		}
		return result;
	}
	
	/**
	 * 传入需要绘制的点的坐标集合，返回一个包含起始点1、控制点1、控制点2、终止点1、控制点3、控制点4 ... 终止点N 的List集合
	 * <p>"//TODO" 标签中的代码是后加入的，若不包含这些代码，则该方法返回的是控制点的集合</p>
	 * @param lists 起止端点集合
	 * @param a 正数(通常可以设置为0)
	 * @param b 正数(通常可以设置为0)
	 * @return
	 */
	public static List<PointFV1d0> getNodesAndCtrlsPointF(List<PointFV1d0> lists, float a, float b) {
		List<PointFV1d0> result = new ArrayList<>();
		if(a == 0 || b == 0) {
			a = 0.25f;
			b = 0.25f;
		}
		//TODO 将起始点加入
		PointFV1d0 start = new PointFV1d0();
		start = lists.get(0);
		result.add(start);
		
		for(int j = 0; j < lists.size() - 1; j++) {
			PointFV1d0 ctrA = new PointFV1d0();
			PointFV1d0 ctrB = new PointFV1d0();
			//TODO 获取终止点
			PointFV1d0 end = new PointFV1d0();
			end = lists.get(j + 1);
			
			float aX, aY, bX, bY;
			if(j == 0) { //第一个元素,控制点A有变化
				aX = lists.get(j).getX() + (lists.get(j + 1).getX() - lists.get(j).getX()) / 4;
				aY = lists.get(j).getY() + (lists.get(j + 1).getY() - lists.get(j).getY()) / 4;
				bX = lists.get(j + 1).getX() - (lists.get(j + 2).getX() - lists.get(j).getX()) * b;
				bY = lists.get(j + 1).getY() - (lists.get(j + 2).getY() - lists.get(j).getY()) * b;
			} else if(j == lists.size() - 2) { //最后一个元素,控制点B有变化
				aX = lists.get(j).getX() + (lists.get(j + 1).getX() - lists.get(j - 1).getX()) * a;
				aY = lists.get(j).getY() + (lists.get(j + 1).getY() - lists.get(j - 1).getY()) * a;
				bX = lists.get(j).getX() - (lists.get(j).getX() - lists.get(j - 1).getX()) / 4; 
				bY = lists.get(j).getY() - (lists.get(j).getY() - lists.get(j - 1).getY()) / 4;
			} else {
				aX = lists.get(j).getX() + (lists.get(j + 1).getX() - lists.get(j - 1).getX()) * a;
				aY = lists.get(j).getY() + (lists.get(j + 1).getY() - lists.get(j - 1).getY()) * a;
				bX = lists.get(j + 1).getX() - (lists.get(j + 2).getX() - lists.get(j).getX()) * b;
				bY = lists.get(j + 1).getY() - (lists.get(j + 2).getY() - lists.get(j).getY()) * b;
			}
			ctrA.setX(aX);
			ctrA.setY(aY);
			ctrB.setX(bX);
			ctrB.setY(bY);
			result.add(ctrA);
			result.add(ctrB);
			
			//TODO 将终止点加入
			result.add(end);
			
		}
		return result;
	}
	
	/**
	 * 根据起始点和终止点，返回两者的两个三阶贝塞尔曲线的控制点坐标集合
	 * @param lists 起止端点集合
	 * @param a 正数(通常可以设置为0)
	 * @param b 正数(通常可以设置为0)
	 * @return
	 */
	public static List<PointFV1d0> getCtrlPointF(List<PointFV1d0> lists, float a, float b) {
		List<PointFV1d0> result = new ArrayList<>();
		if(a == 0 || b == 0) {
			a = 0.25f;
			b = 0.25f;
		}
		
		for(int j = 0; j < lists.size() - 1; j++) {
			PointFV1d0 ctrA = new PointFV1d0();
			PointFV1d0 ctrB = new PointFV1d0();
			float aX, aY, bX, bY;
			if(j == 0) { //第一个元素,控制点A有变化
				aX = lists.get(j).getX() + (lists.get(j + 1).getX() - lists.get(j).getX()) / 4;
				aY = lists.get(j).getY() + (lists.get(j + 1).getY() - lists.get(j).getY()) / 4;
				bX = lists.get(j + 1).getX() - (lists.get(j + 2).getX() - lists.get(j).getX()) * b;
				bY = lists.get(j + 1).getY() - (lists.get(j + 2).getY() - lists.get(j).getY()) * b;
			} else if(j == lists.size() - 2) { //最后一个元素,控制点B有变化
				aX = lists.get(j).getX() + (lists.get(j + 1).getX() - lists.get(j - 1).getX()) * a;
				aY = lists.get(j).getY() + (lists.get(j + 1).getY() - lists.get(j - 1).getY()) * a;
				bX = lists.get(j).getX() - (lists.get(j).getX() - lists.get(j - 1).getX()) / 4; 
				bY = lists.get(j).getY() - (lists.get(j).getY() - lists.get(j - 1).getY()) / 4;
			} else {
				aX = lists.get(j).getX() + (lists.get(j + 1).getX() - lists.get(j - 1).getX()) * a;
				aY = lists.get(j).getY() + (lists.get(j + 1).getY() - lists.get(j - 1).getY()) * a;
				bX = lists.get(j + 1).getX() - (lists.get(j + 2).getX() - lists.get(j).getX()) * b;
				bY = lists.get(j + 1).getY() - (lists.get(j + 2).getY() - lists.get(j).getY()) * b;
			}
			ctrA.setX(aX);
			ctrA.setY(aY);
			ctrB.setX(bX);
			ctrB.setY(bY);
			result.add(ctrA);
			result.add(ctrB);
		}
		return result;
	}
	
	/**
	 * 将经纬度转化为屏幕坐标上的坐标值
	 * @param width
	 * @param height
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param points
	 * @return
	 */
	public static List<PointV1d0> turnToIntPoints(int width, int height, float left, float right, float bottom, float top, List<PointFV1d0> points) {
		List<PointV1d0> result = new ArrayList<>();
		for(PointFV1d0 point : points) {
			PointV1d0 position = GeographySingletonV1d0.getInstance().world2Screen("none", width, height, left, right, bottom, top, point.getX(), point.getY());
			result.add(position);
		}
		return result;
	}
	
	/**
	 * 根据输入的起始点、终止点及控制点坐标，返回 三阶贝塞尔曲线 上的点的集合
	 * @param p1  起始点坐标
	 * @param c1  控制点1坐标
	 * @param c2  控制点2坐标
	 * @param p2  终止点坐标
	 * @return
	 */
	public static List<PointFV1d0> getBezierPointFs(PointFV1d0 p1, PointFV1d0 c1, PointFV1d0 c2, PointFV1d0 p2) {
		List<PointFV1d0> points = Arrays.asList(p1, c1, c2, p2);
		int n = points.size() - 1; //贝塞尔曲线的阶数(在此显然为 n = 3)。
		List<PointFV1d0> result = new ArrayList<>();
		for(float r = 0; r < 1; r += 0.01) {
			PointFV1d0[] p = new PointFV1d0[n + 1];
			for(int i = 0; i < points.size(); i++) {
				p[i] = new PointFV1d0(points.get(i).getX(), points.get(i).getY());
			}
			for(int j = 0; j < points.size(); j++) {
				for(int k = 0; k < n - j; k++) {
					float x =  (1 - r) * p[k].getX() + r * p[k + 1].getX();
					float y =  (1 - r) * p[k].getY() + r * p[k + 1].getY();
					BigDecimal x0 = new BigDecimal(x);
					BigDecimal y0 = new BigDecimal(y);
					float needX = x0.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					float needY = y0.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					p[k].setX(needX);
					p[k].setY(needY);
					/** 未做保留小数处理的代码
					p[k].setX( (1 - r) * p[k].getX() + r * p[k + 1].getX() );
					p[k].setY( (1 - r) * p[k].getY() + r * p[k + 1].getY() );
					*/
				}
			}
			result.add(p[0]);
		}
		return result;
	}
}
