package org.xmm.falsework.util.dynamicdb;

import java.lang.annotation.*;

/**
 * 动态数据源注解
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DynamicDSAnno {
    DataSourceEnum ds();
}
