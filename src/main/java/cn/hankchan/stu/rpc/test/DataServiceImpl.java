package cn.hankchan.stu.rpc.test;

public class DataServiceImpl implements DataService {

	@Override
	public String getData(boolean flag) {
		return flag == true ? DataManager.getData() + "Success to get Data.." : "Failure to get Data..";
	}

}
