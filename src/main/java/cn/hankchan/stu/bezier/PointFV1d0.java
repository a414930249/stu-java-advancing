package cn.hankchan.stu.bezier;

public class PointFV1d0 {
	//x坐标
	private float x;
	//y坐标
	private float y;
		
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public PointFV1d0(){}
	
	/**
	 * 构造函数
	 * @param x
	 * @param y
	 */
	public PointFV1d0(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
}
