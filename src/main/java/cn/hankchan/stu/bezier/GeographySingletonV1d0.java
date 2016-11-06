package cn.hankchan.stu.bezier;
//import swift.geography.service.LngLatProjectionV1d0;
@Deprecated
public class GeographySingletonV1d0{
	//地球半径和圆周率
    private static float EARTH_RADIUS = 6371f;
    private static float PI = (float)Math.PI;
    
	/**
	 * 单例模式
	 * @author Administrator
	 *
	 */
	private static class GeographySingletonV1d0Holder {
        public static GeographySingletonV1d0 instance = new GeographySingletonV1d0();
    }
	public static GeographySingletonV1d0 getInstance()
	{
		return GeographySingletonV1d0Holder.instance;
	}	
	
	/**
	 * 利用球面公式计算两点间的距离
	 * @param startLongitude:起始经度
	 * @param startLatitude:起始纬度
	 * @param endLongitude:终点经度
	 * @param endLatitude:终点纬度
	 * @return
	 */
    public float calculateDistance(float startLongitude, float startLatitude, float endLongitude, float endLatitude)
    {
        float radLat1 = startLatitude * PI / 180;
        float radLat2 = endLatitude * PI / 180;
        float a = radLat1 - radLat2;
        float b = startLongitude * PI / 180 - endLongitude * PI / 180;
        float s = (float)(2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2))));
        s = EARTH_RADIUS * s;
        return s;
    }
    
    /**
     * 将地图经纬度坐标换算成投影下的屏幕坐标
     * @param projName: 投影名称，取none、Mercator、UTM、Lambertor等
     * @param imgWidth: 屏幕宽度
     * @param imgHeight: 屏幕高度
     * @param startLongitude: 屏幕左边经度(如果是Google Mercator投影，则为西北角的经度)
     * @param endLongitude: 屏幕右边经度(如果是Google Mercator投影，则为当前的放大级别)
     * @param startLatitude: 屏幕下边纬度(如果是Google Mercator投影，则为西北角的纬度)
     * @param endLatitude:屏幕上边纬度(如果是Google Mercator投影，则为0)
     * @param currentLongitude：当前的经度
     * @param currentLatitude：当前的纬度
     * @return
     */
    public PointV1d0 world2Screen(String projName, int imgWidth, int imgHeight, float startLongitude, float endLongitude, float startLatitude, float endLatitude, float currentLongitude, float currentLatitude)
    {
        PointV1d0 pt = new PointV1d0();
        if (projName == null || "none".equals(projName.toLowerCase()))
        {
            //没有投影，认为是等经纬度投影
            pt = W2SNoneProj(imgWidth, imgHeight, startLongitude, endLongitude, startLatitude, endLatitude, currentLongitude, currentLatitude);
        }
        else if ("googlemercator".equals(projName.toLowerCase()))
        {
            //Google Map中的墨卡托投影
            //pt = GoogleMercator.CalScreenPoint((int)endLongitude, startLongitude, startLatitude, currentLongitude, currentLatitude);
        }        
        return pt;
    }
    
    /**
     * 计算起点到终点的线段与水平线的之间的夹角
     *    E2       E1
     *      \     / 
     *       \   /
     *        \ /
     * ------- S-------H
     *        / \
     *       /   \
     *      /     \
     *     E3      E4
     * S为起点，E1、E2、E3、E4为不同象限的终点，分别计算出HSE1、HSE2、HSE3、HSE4的夹角
     * @param startPoint: 起点（经纬度坐标）
     * @param endPoint: 终点（经纬度坐标）
     * @param type: 类型：0＝上小下大（例如屏幕坐标），1＝上大下小（例如地理坐标）
     * @return
     */
    public float CalculateAngle(PointFV1d0 startPoint, PointFV1d0 endPoint, int type)
    {
        //求出该撤离线路的斜率
        float dy = endPoint.getY() - startPoint.getY();
        float dx = endPoint.getX() - startPoint.getX();
        float theta = 90;
        if (dx != 0)
        {
            //求出夹角
            float tangent = dy / dx;
            theta = (float)(Math.atan(tangent) * 180 / Math.PI);
        }

        if (type == 0)
        {
            //上小下大（例如屏幕坐标）
            if (endPoint.getY() > startPoint.getY())
            {
                //终点在起点的下方，则增加180度
                theta += 180;
            }
        }
        else
        {
            //上大下小（例如地理坐标）
            if (endPoint.getY() < startPoint.getY())
            {
                theta += 180;
            }
        }
        return theta;
    }

    /**
     * 计算从起点到终点方向，前进多少公里的经纬度坐标
     * @param startLongitude: 起点经度
     * @param startLatitude: 起点纬度
     * @param endLongitude: 终点经度
     * @param endLatitude: 终点纬度
     * @param distance: 从起点向终点前进的距离
     * @param flag: 方向（0=起点向终点，1=起点背向终点）
     * @return
     */
//    public PointFV1d0 calculteNewPostition(float startLongitude, float startLatitude, float endLongitude, float endLatitude, float distance, int flag)
//    {
//        //将起点和终点经纬度坐标换算成假想中的米坐标
//    	PointFV1d0 startPoint = LngLatProjectionV1d0.d2m(startLongitude, startLatitude);
//    	PointFV1d0 endPoint = LngLatProjectionV1d0.d2m(endLongitude, endLatitude);
//        //求出从起点向终点前进N公里的点的米坐标
//    	PointFV1d0 innerPoint = GeometrySingletonV1d0.getInstance().getStretchInnerPoint(startPoint, endPoint, distance * 1000, flag);
//        //将米坐标换算成经纬度坐标
//    	PointFV1d0 currentPoint = LngLatProjectionV1d0.m2d(innerPoint.getX(), innerPoint.getY());
//        return currentPoint;
//    }
    
    /**
     * 构造函数
     */
    private GeographySingletonV1d0(){}

    /**
     * 计算当前经纬度坐标对应屏幕的像素坐标
     * @param imgWidth: 屏幕宽度
     * @param imgHeight: 屏幕高度
     * @param startLongitude: 屏幕左边经度
     * @param endLongitude: 屏幕右边经度
     * @param startLatitude: 屏幕下边纬度
     * @param endLatitude: 屏幕上边纬度
     * @param currentLongitude: 当前的经度
     * @param currentLatitude: 当前的纬度
     * @return
     */
    private PointV1d0 W2SNoneProj(int imgWidth, int imgHeight, float startLongitude, float endLongitude, float startLatitude, float endLatitude, float currentLongitude, float currentLatitude)
    {
    	PointV1d0 p = new PointV1d0();
        int x = (int)Math.ceil((currentLongitude - startLongitude) * (imgWidth - 1) / (endLongitude - startLongitude));
        int y = (int)Math.ceil((endLatitude - currentLatitude) * (imgHeight - 1) / (endLatitude - startLatitude));
        p.setX(x);
        p.setY(y);
        return p;
    }
}
