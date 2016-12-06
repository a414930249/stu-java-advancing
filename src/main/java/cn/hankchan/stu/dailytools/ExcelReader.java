package cn.hankchan.stu.dailytools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

/**
 * 操作Excel表格的功能类
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 09:25:26 - 6 Dec 2016
 * @detail
 */
public class ExcelReader {
	
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    public static void main(String[] args) throws IOException {
        try {
        	// 对读取Excel表格标题测试 D:\qxt\ExcelToXML\data.xls
            InputStream is = new FileInputStream("d:\\qxt\\ExcelToXML\\data.xls");
            ExcelReader excelReader = new ExcelReader();
            String[] title = excelReader.readExcelTitle(is);
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }

            // 对读取Excel表格内容测试
            InputStream is2 = new FileInputStream("d:\\qxt\\ExcelToXML\\data.xls");
            // 生成一个存放表格内容的map
            Map<Integer, String> map = excelReader.readExcelContent(is2);
            System.out.println("获得Excel表格的内容:");
            /*
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
                String[] valueArray = map.get(i).split("    ");
                System.out.println(valueArray);
                
            }
            */
            /*********************************************************************************************************/
            /*********************************************************************************************************/
            /********************************* 将Excel表格的数据根据需求生成XML文件  **************************************/
            // DocumentHelper 提供创建对象的方法
            Document document = DocumentHelper.createDocument();
            // 添加节点信息
            Element rootElement = document.addElement("TrafficRisks");
            for (int i = 1; i <= map.size(); i++) {
            	System.out.println(map.get(i));
	            String[] valueArray = map.get(i).split("    ");
	            System.out.println(valueArray);
	            // 继续添加其子节点信息
	            Element element = rootElement.addElement("RiskPoint");
            	if(i >= 40) {
            		String aStr = valueArray[5].toString();
            		String[] a = aStr.split(",");
            		float longitude = Float.parseFloat(a[0]) + (Float.parseFloat(a[1]) / 60) + (Float.parseFloat(a[2]) / 3600);
            		String bStr = valueArray[6].toString();
            		String[] b = bStr.split(",");
            		float latitude = Float.parseFloat(b[0]) + (Float.parseFloat(b[1]) / 60) + (Float.parseFloat(b[2]) / 3600);
            		String cStr = valueArray[8].toString();
            		String[] c = cStr.split(",");
            		float endLongitude = Float.parseFloat(c[0]) + (Float.parseFloat(c[1]) / 60) + (Float.parseFloat(c[2]) / 3600);
            		String dStr = valueArray[9].toString();
            		String[] d = dStr.split(",");
            		float endLatitude = Float.parseFloat(d[0]) + (Float.parseFloat(d[1]) / 60) + (Float.parseFloat(d[2]) / 3600);
            		element.addAttribute("id", valueArray[1]);
            		element.addAttribute("longitude", String.valueOf(longitude));
		            element.addAttribute("latitude", String.valueOf(latitude));
		            element.addAttribute("type", getType(valueArray[11]));
		            element.addAttribute("stationId", "");
		            element.addAttribute("description", valueArray[0] + " " + valueArray[1] + "处，起点位置(" 
		            					 + String.valueOf(longitude) + "E," + String.valueOf(latitude) + "N)，终点位置(" + String.valueOf(endLongitude) + "E," + String.valueOf(endLatitude) + "N)，平均海拔" 
		            					 + ((Float.parseFloat(valueArray[7]) + Float.parseFloat(valueArray[10])) /2) + "米，主要气象灾害:" 
		            					 + valueArray[11]);
            	} else {
		            element.addAttribute("id", valueArray[1]);
		            element.addAttribute("longitude", valueArray[5]);
		            element.addAttribute("latitude", valueArray[6]);
		            element.addAttribute("type", getType(valueArray[11]));
		            element.addAttribute("stationId", "");
		            element.addAttribute("description", valueArray[0] + " " + valueArray[1] + "处，起点位置(" 
		            					 + valueArray[5] + "E," + valueArray[6] + "N)，终点位置(" + valueArray[8] + "E," + valueArray[9] + "N)，平均海拔" 
		            					 + String.valueOf(((Float.parseFloat(valueArray[7]) + Float.parseFloat(valueArray[10])) /2)) + "米，主要气象灾害:" 
		            					 + valueArray[11]);
            	}
            }
            // 输出
            Writer fileWriter = new FileWriter("d:\\qxt\\ExcelToXML\\hankResult.xml");
            XMLWriter xmlWriter = new XMLWriter(fileWriter);
            xmlWriter.write(document);  
            xmlWriter.flush();  
            xmlWriter.close(); 
            /*********************************************************************************************************/
            /*********************************************************************************************************/
            /*********************************************************************************************************/
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }

    public static String getType(String type) {
    	String result = "";
    	if(type.equals("能见度")) {
    		result = "NJD";
    	}
    	if(type.equals("路面积冰")) {
    		result = "DLJB";
    	}
    	if(type.equals("山体滑坡")) {
    		result = "STHP";
    	}
    	if(type.equals("泥石流")) {
    		result = "NSL";
    	}
    	if(type.equals("横风")) {
    		result = "HF";
    	}
    	if(type.equals("大雾")) {
    		result = "DW";
    	}
    	if(type.equals("强降雨")) {
    		result = "QJY";
    	}
    	if(type.equals("雨")) {
    		result = "RAIN";
    	}
    	return result;
    }
    
    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle(InputStream is) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    @SuppressWarnings({ "unused", "deprecation" })
	private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    @SuppressWarnings({ "unused", "deprecation" })
	private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
	private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
        	// 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
            	// 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                	// 取得当前Cell的数值ֵ
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
            	// 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
    
}