package org.xmm.falsework.xmm.util.dynamicdb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源处理类
 * 
 * @author org.xmm.falsework.xmm
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 返回datasource Map的key。而不是返回datasource本身
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("use db:"+(HandleDataSource.getDataSource()==null?"default":HandleDataSource.getDataSource()));
        return HandleDataSource.getDataSource();
    }
    
}
