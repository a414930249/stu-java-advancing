package cn.hankchan.stu.cache;

public class Main {

	public static void main(String[] args) {
		MyCacheService cacheService = new MyCacheService();
		cacheService.getAccount("cjm");
		System.out.println("------------------------");
		cacheService.getAccount("cjm");
		System.out.println("------------------------");
		cacheService.getAccount("cjm");
		System.out.println("------------------------");
		cacheService.reload();
		cacheService.getAccount("cjm");
		System.out.println("------------------------");
		cacheService.getAccount("cjm");
	}
}
