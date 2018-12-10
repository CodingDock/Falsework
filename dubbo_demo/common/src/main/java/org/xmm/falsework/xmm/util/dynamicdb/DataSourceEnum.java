package org.xmm.falsework.xmm.util.dynamicdb;

/**
 * 只读数据库名称枚举类，有几个只读实例添加几个
 * @author org.xmm.falsework.xmm
 */
public enum DataSourceEnum {
    
    MASTER("master"),SLAVE_01("SLAVEDB_01");
    
    private String name;

    DataSourceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
