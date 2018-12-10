package org.xmm.falsework.xmm.util.dynamicdb;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态数据源切面类,order保证切面执行顺序
 * @author org.xmm.falsework.xmm
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class DynamicDataSourceAspect {


    @Pointcut("execution(* org.xmm.falsework.service.impl.*.*(..)) && @annotation(DynamicDSAnno)")
    public void dataSourcePointcut() {}

    @Before("dataSourcePointcut()")
    public void doBeforeChangeDS(JoinPoint joinPoint){

        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        DynamicDSAnno annotationClass = method.getAnnotation(DynamicDSAnno.class);//获取方法上的注解
        if(annotationClass == null){
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicDSAnno.class);//获取类上面的注解
            if(annotationClass == null) return;
        }
        //获取注解上的数据源的值的信息
        DataSourceEnum dataSourceKey = annotationClass.ds();
        if(dataSourceKey !=null){
            //给当前的执行SQL的操作设置特殊的数据源的信息
            HandleDataSource.putDataSource(dataSourceKey);
        }
        log.info("AOP动态切换数据源，className:"+joinPoint.getTarget().getClass().getName()+";methodName:"+method.getName()+";dataSourceKey:"+(dataSourceKey==null?"默认数据源":dataSourceKey.getName()));

    }

    @Before("dataSourcePointcut()")
    public void doAfterChangeDS(JoinPoint joinPoint){
        HandleDataSource.clear();
    }
    
    
}
