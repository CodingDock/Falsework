package org.xmm.falsework.util.dynamicdb;

/**
 * 数据源线程绑定处理类，用于指定线程使用哪个数据源
 * @author xmm
 */
public class HandleDataSource {
    private static final ThreadLocal<DataSourceEnum> holder = new ThreadLocal<DataSourceEnum>();

    public static void putDataSource(DataSourceEnum sdb) {
        holder.set(sdb);
    }

    public static DataSourceEnum getDataSource() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
