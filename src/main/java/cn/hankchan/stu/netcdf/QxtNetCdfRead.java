package cn.hankchan.stu.netcdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.Dimension;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class QxtNetCdfRead {

	@Test
	public void testGetDataFromVariable() {
		String filename = "D:/NPData/guangdong/grapes/2016102100/rain.nc";
		NetcdfFile ncfile = null;
		try {
			ncfile = NetcdfFile.open(filename);
			List<Variable> varBeans = ncfile.getVariables();
			for(Variable varBean : varBeans) {
				System.out.println(varBean.getDODSName());
				if (null != varBean) {
					Array all = varBean.read();
					String result = NCdumpW.printArray(all, varBean.getDODSName(), null);
					String[] resArr = result.split("},");
					System.out.println(resArr.length);
					System.out.println(result);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从NC文件获取网格数据
	 * @return
	 */
	public static List<Float> testGetNcValue() {
		List<Float> gridDatas = new ArrayList<>();
		String filename = "D:/NPData/guangdong/swanradamosaic/20161021/201610210600.nc";
		NetcdfFile ncfile = null;
		try {
			ncfile = NetcdfFile.open(filename);
			List<Dimension> dimensions = ncfile.getDimensions();
			List<Variable> variables = ncfile.getVariables();
			for(Variable variable : variables) {
				Variable varBean = ncfile.findVariable(variable.getDODSName());
				if(null != varBean) {
					if(dimensions.size() == 4) {  // 确保是模式预报数据（4个维度）
						// 获取四个维度的长度值
						int oneDimensionLength = dimensions.get(0).getLength();  // level
						int twoDimensionLength = dimensions.get(1).getLength();  // time
						int threeDimensionLength = dimensions.get(2).getLength();  // rows
						int fourDimensionLength = dimensions.get(3).getLength();  // cols
						// 遍历第一维度：level
						for(int i = 0; i < oneDimensionLength - 20; i++) {
							// 由于第二维度（时间序列time）为1，不需要遍历
							if(twoDimensionLength == 1) {
								// 读取网格数据
								for(int j = 0; j < threeDimensionLength; j++) {
									for(int k = 0; k < fourDimensionLength; k++) {
										int[] origin = new int[] {i, 0, j, k};  // 定义位置
										int[] size = new int[] {1, 1, 1, 1};  // 确定数量
										Array data4D = varBean.read(origin, size);
										// 返回指定位置的数据
										String ncValue = NCdumpW.printArray(data4D, variable.getDODSName(), null); 
										Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");  
									       Matcher matcher = pattern.matcher(ncValue);  
									       while (matcher.find()) {  
									           String str = matcher.group(0);  
									           if(!str.equals("-1")) {
									        	   //System.out.println("value= " + str + ", when i= " + i + ";j= " + j + ";k=" + k);
									           }
									           float v = Float.parseFloat(str);
									           gridDatas.add(v);
									       }
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidRangeException e) {
			e.printStackTrace();
		}
		return gridDatas;
	}
	
	/**
	 * 测试读取NC文件，并且希望根据四个维度的值获取相应的数据
	 * @throws InvalidRangeException
	 */
	@Test
	public void testReadDimensionsAndVariables() throws InvalidRangeException {
		//D:\NPData\guangdong\swanradamosaic\20161021\201610210000.nc
		String filename = "D:/NPData/guangdong/swanradamosaic/20161021/201610210600.nc";
		NetcdfFile ncfile = null;
		try {
			ncfile = NetcdfFile.open(filename);
			// read Dimensions
			List<Dimension> dimensions = ncfile.getDimensions();
			for(Dimension dimension : dimensions) {
				System.out.println("Dimensions Name = " + dimension.getDODSName() + "; lenth = " + dimension.getLength());
			}
			// read Variables
			List<Variable> variables = ncfile.getVariables();
			for(Variable variable : variables) {
				System.out.println("Variables Name = " + variable.getDODSName() + "; NameAndDimension = " + variable.getNameAndDimensions() + "; ElementSize = " + variable.getElementSize());
				Variable varBean = ncfile.findVariable(variable.getDODSName());
				if(varBean != null) {
					/*
					// 获取全部Array数据
					Array all = varBean.read();
					// 得到全部数据
					String result = NCdumpW.printArray(all, variable.getDODSName(), null);
					*/
					// 选择读取的位置
					int[] origin = new int[] {0,0,23,134};
					// 读取的数量(从读取位置开始计数)
					int[] size = new int[] {1,1,1,1};
					// 获取指定条件的Array数据
					Array data4D = varBean.read(origin, size);
					// 返回指定位置的数据
					String ncValue = NCdumpW.printArray(data4D, variable.getDODSName(), null);
					System.out.println("读取从每一个维度的0开始，数量都为1：\n" + ncValue);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadAll() {
		String filename = "D:/NPData/guangdong/grapes/2016102100/rain.nc";
		NetcdfFile ncfile = null;
		try {
			ncfile = NetcdfFile.open(filename);
			String variable = "rain";
			Variable varBean = ncfile.findVariable(variable);
			if (null != varBean) {
				Array all = varBean.read();
				String result = NCdumpW.printArray(all, variable, null);
				System.out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
