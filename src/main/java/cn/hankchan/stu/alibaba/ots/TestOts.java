package cn.hankchan.stu.alibaba.ots;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.Column;
import com.alicloud.openservices.tablestore.model.ColumnValue;
import com.alicloud.openservices.tablestore.model.CreateTableRequest;
import com.alicloud.openservices.tablestore.model.ListTableResponse;
import com.alicloud.openservices.tablestore.model.PrimaryKey;
import com.alicloud.openservices.tablestore.model.PrimaryKeyBuilder;
import com.alicloud.openservices.tablestore.model.PrimaryKeySchema;
import com.alicloud.openservices.tablestore.model.PrimaryKeyType;
import com.alicloud.openservices.tablestore.model.PrimaryKeyValue;
import com.alicloud.openservices.tablestore.model.PutRowRequest;
import com.alicloud.openservices.tablestore.model.RowPutChange;
import com.alicloud.openservices.tablestore.model.TableMeta;
import com.alicloud.openservices.tablestore.model.TableOptions;

import cn.hankchan.stu.netcdf.QxtNetCdfRead;
import ucar.ma2.InvalidRangeException;

public class TestOts {

	@Test
	public void mainTest() throws InvalidRangeException, IOException {
		System.out.println("start...");
		final String endPoint = "http://portal.cn-shenzhen.ots.aliyuncs.com";
        final String accessId = "Ul47qjmRrbRTGy5M";
        final String accessKey = "6dukgSrjaNq77mizxincS5MEYYJtYB";
        final String instanceName = "portal";
        SyncClient client = new SyncClient(endPoint, accessId, accessKey,
                instanceName);
        String tableName = "begzcappi";
        // 提供主键及其类型
        Map<String, PrimaryKeyType> primaryKeys = new HashMap<>();
        primaryKeys.put("ObserveTime", PrimaryKeyType.INTEGER);
        primaryKeys.put("LayerIndex", PrimaryKeyType.INTEGER);
        primaryKeys.put("TimeSerial", PrimaryKeyType.INTEGER);
        // 提供非主键列名
        List<String> columnNames = Arrays.asList("Cols", "Rows", "Left", "Top", "LongitudeInterval", 
        		"LatitudeInterval", "LongitudeUnit", "LatitudeUnit", "DataType", "Factor", "DataLength");
        // 初始化表
        initTable(client, tableName, primaryKeys);
        
        // 获取NC文件的数据
        List<Float> datas = QxtNetCdfRead.testGetNcValue();
        // 数据写入OTS表中
        writeDatas(client, tableName, datas);
        listTable(client);
	}
	
	/**
	 * 写入数据
	 * @param columnNames
	 * @param datas
	 * @throws InvalidRangeException
	 * @throws IOException 
	 */
	public static void writeDatas(SyncClient client, String tableName, List<Float> datas) throws InvalidRangeException, IOException {
		// 构造主键值
		PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn("ObserveTime", PrimaryKeyValue.fromString("201610210600"));
        primaryKeyBuilder.addPrimaryKeyColumn("LayerIndex", PrimaryKeyValue.fromString("0"));
        primaryKeyBuilder.addPrimaryKeyColumn("TimeSerial", PrimaryKeyValue.fromString("0"));
        PrimaryKey primaryKey = primaryKeyBuilder.build();
        RowPutChange rowPutChange = new RowPutChange(tableName, primaryKey);
        // 加入一些属性列
        rowPutChange.addColumn(new Column("Cols", ColumnValue.fromDouble(1050)));
        rowPutChange.addColumn(new Column("Rows", ColumnValue.fromDouble(880)));
        rowPutChange.addColumn(new Column("Left", ColumnValue.fromDouble(108.5)));
        rowPutChange.addColumn(new Column("Top", ColumnValue.fromDouble(27.0)));
        rowPutChange.addColumn(new Column("LongitudeInterval", ColumnValue.fromDouble(0.01)));
        rowPutChange.addColumn(new Column("LatitudeInterval", ColumnValue.fromDouble(0.01)));
        rowPutChange.addColumn(new Column("LongitudeUnit", ColumnValue.fromDouble(1.0)));
        rowPutChange.addColumn(new Column("LatitudeUnit", ColumnValue.fromDouble(1.0)));
        rowPutChange.addColumn(new Column("DataType", ColumnValue.fromString("byte")));
        rowPutChange.addColumn(new Column("Factor", ColumnValue.fromDouble(1.0)));
        // 将数据转为byte[]类型
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bas);
        for (float f : datas) {
        	ds.writeFloat(f);
        }
        byte[] bytes = bas.toByteArray();
        rowPutChange.addColumn(new Column("DataLength", ColumnValue.fromBinary(bytes)));
        
		client.putRow(new PutRowRequest(rowPutChange));
	}
	
	/**
	 * 初始化表格
	 * @param client
	 * @param primaryKeys:主键及其类型，可选：PrimaryKeyType.INTEGER; PrimaryKeyType.BINARY; PrimaryKeyType.STRING;
	 * @param columnNames:非主键列
	 */
	public static void initTable(SyncClient client, String tableName, 
			Map<String, PrimaryKeyType> primaryKeys) {
		// 创建表
		TableMeta tableMeta = new TableMeta(tableName);
		// 设置主键及其类型
		for(Entry<String, PrimaryKeyType> entry : primaryKeys.entrySet()) {
			tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema(entry.getKey(), entry.getValue()));
		}
		int timeToLive = -1; // 数据的过期时间, 单位秒, -1代表永不过期. 假如设置过期时间为一年, 即为 365 * 24 * 3600.
        int maxVersions = 1; // 保存的最大版本数, 设置为1即代表每列上最多保存1个最新的版本.
        
        TableOptions tableOptions = new TableOptions(timeToLive, maxVersions);
        CreateTableRequest request = new CreateTableRequest(tableMeta, tableOptions);
        // 执行创建表
        client.createTable(request);
        // 等待表load完毕.
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 创建列名
        
        
	}
	
    /**
     * list table查看表的列表
     * @param client
     */
    public static void listTable(SyncClient client) {
        ListTableResponse response = client.listTable();
        System.out.println("表的列表如下：");
        for (String tableName : response.getTableNames()) {
            System.out.println(tableName);
        }
    }
	
}
