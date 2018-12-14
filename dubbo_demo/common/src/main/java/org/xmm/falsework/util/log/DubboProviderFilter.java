package org.xmm.falsework.util.log;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

public class DubboProviderFilter implements Filter {

    static Logger logger= LoggerFactory.getLogger(DubboProviderFilter.class);



    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // before filter ...
        Result result;

        try {

            Map<String, String> context = invocation.getAttachments();
            //启用判断 todo
            if(context.get(MDCConstants.REQUEST_ID_MDC_KEY) !=null){
                MDC.put(MDCConstants.REQUEST_ID_MDC_KEY, context.get(MDCConstants.REQUEST_ID_MDC_KEY));
                MDC.put(MDCConstants.REQUEST_SEQ_MDC_KEY, context.get(MDCConstants.REQUEST_SEQ_MDC_KEY));
                MDC.put(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY, context.get(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY)+"0");
                MDC.put(MDCConstants.URI_MDC_KEY, context.get(MDCConstants.URI_MDC_KEY));

            }
            long stamp=System.currentTimeMillis();
            logger.info("提供者调用耗时记录开始："+invoker.getInterface()+"，\ttimestamp:"+System.currentTimeMillis());
            result = invoker.invoke(invocation);
            logger.info("提供者调用耗时记录结束："+invoker.getInterface()+"，\ttimestamp:"+(System.currentTimeMillis()-stamp));
            
        } finally {
            MDC.clear();//must be,threadLocal  
        }
        
        return result;
    }
    
    
    
}
