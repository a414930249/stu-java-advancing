package cn.hankchan.stu.alibaba.ots;

import com.alicloud.openservices.tablestore.ClientException;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.TableStoreException;
import com.alicloud.openservices.tablestore.model.*;

/**
 * 测试OTS操作
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 15:35:02 - 7 Dec 2016
 * @detail
 */
public class TableOperationSample {

    /**
     * 本示例中建立一张表,名为sampleTable,只含有一个主键, 主键名为pk.
     */
    private static final String TABLE_NAME = "test_begzcappi";
    private static final String KEY_NAME_1 = "ObserveTime";
    private static final String KEY_NAME_2 = "LayerIndex";
    private static final String KEY_NAME_3 = "TimeSerial";

    public static void main(String[] args) throws Exception {
        final String endPoint = "";
        final String accessId = "";
        final String accessKey = "";
        final String instanceName = "portal";
        // 创建OTSClient对象, 使用client读写表
        SyncClient client = new SyncClient(endPoint, accessId, accessKey,
                instanceName);
        try {
            // 创建表
            createTable(client);
            
            // list table查看表的列表
            listTable(client);
            // 查看表的属性
            describeTable(client);
            // 更新表的属性
            updateTable(client);
            // update table完之后查看表的属性
            describeTable(client);
        } catch (TableStoreException e) {
            System.err.println("操作失败，详情：" + e.getMessage());
            System.err.println("Request ID:" + e.getRequestId());
        } catch (ClientException e) {
            System.err.println("请求失败，详情：" + e.getMessage());
        } finally {
        // 关闭Client
        	client.shutdown();
        }
    }
    
    /**
     * 创建表
     * @param client
     */
    public static void createTable(SyncClient client) {
        TableMeta tableMeta = new TableMeta(TABLE_NAME);
        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema(KEY_NAME_1, PrimaryKeyType.INTEGER));
        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema(KEY_NAME_2, PrimaryKeyType.INTEGER));
        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema(KEY_NAME_3, PrimaryKeyType.INTEGER));
        int timeToLive = -1; // 数据的过期时间, 单位秒, -1代表永不过期. 假如设置过期时间为一年, 即为 365 * 24 * 3600.
        int maxVersions = 1; // 保存的最大版本数, 设置为3即代表每列上最多保存3个最新的版本.

        TableOptions tableOptions = new TableOptions(timeToLive, maxVersions);

        CreateTableRequest request = new CreateTableRequest(tableMeta, tableOptions);

        client.createTable(request);
    }

    /**
     * 更新表的属性
     * @param client
     */
    public static void updateTable(SyncClient client) {
        int timeToLive = -1;
        int maxVersions = 1; //更新最大版本数为5.

        TableOptions tableOptions = new TableOptions(timeToLive, maxVersions);

        UpdateTableRequest request = new UpdateTableRequest(TABLE_NAME);
        request.setTableOptionsForUpdate(tableOptions);

        client.updateTable(request);
    }

    /**
     * 查看表的属性
     * @param client
     */
    public static void describeTable(SyncClient client) {
        DescribeTableRequest request = new DescribeTableRequest(TABLE_NAME);
        DescribeTableResponse response = client.describeTable(request);

        TableMeta tableMeta = response.getTableMeta();
        System.out.println("表的名称：" + tableMeta.getTableName());
        System.out.println("表的主键：");
        for (PrimaryKeySchema primaryKeySchema : tableMeta.getPrimaryKeyList()) {
            System.out.println(primaryKeySchema);
        }
        TableOptions tableOptions = response.getTableOptions();
        System.out.println("表的TTL:" + tableOptions.getTimeToLive());
        System.out.println("表的MaxVersions:" + tableOptions.getMaxVersions());
        ReservedThroughputDetails reservedThroughputDetails = response.getReservedThroughputDetails();
        System.out.println("表的预留读吞吐量："
                + reservedThroughputDetails.getCapacityUnit().getReadCapacityUnit());
        System.out.println("表的预留写吞吐量："
                + reservedThroughputDetails.getCapacityUnit().getWriteCapacityUnit());
    }

    /**
     * 删除表
     * @param client
     */
    public static void deleteTable(SyncClient client) {
        DeleteTableRequest request = new DeleteTableRequest(TABLE_NAME);
        client.deleteTable(request);
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
