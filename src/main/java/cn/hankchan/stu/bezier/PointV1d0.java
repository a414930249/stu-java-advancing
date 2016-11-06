package cn.hankchan.stu.bezier;

/**
 * 定义一个整型的几何点
 * @author Administrator
 *
 */
public class PointV1d0 {
	//X坐标
	private int x;
	//Y坐标
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public PointV1d0(){}
	
	public PointV1d0(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
