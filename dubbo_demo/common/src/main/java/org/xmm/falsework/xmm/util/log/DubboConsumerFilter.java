package org.xmm.falsework.xmm.util.log;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

public class DubboConsumerFilter  implements Filter {
    static Logger logger= LoggerFactory.getLogger(DubboConsumerFilter.class);


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // before filter ...
        
        //启用判断 todo
        if(MDC.get(MDCConstants.REQUEST_ID_MDC_KEY) != null){


            Map<String, String> context = invocation.getAttachments();

            context.put(MDCConstants.REQUEST_ID_MDC_KEY,MDC.get(MDCConstants.REQUEST_ID_MDC_KEY));
            context.put(MDCConstants.REQUEST_SEQ_MDC_KEY,MDC.get(MDCConstants.REQUEST_SEQ_MDC_KEY)+"0");
            context.put(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY,MDC.get(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY)+"00");
            context.put(MDCConstants.URI_MDC_KEY,MDC.get(MDCConstants.URI_MDC_KEY));


        }
        long stamp=System.currentTimeMillis();
        logger.info("消费者调用耗时记录开始："+invoker.getInterface()+"，\ttimestamp:"+System.currentTimeMillis());
        Result result = invoker.invoke(invocation);
        // after filter ...
        logger.info("消费者调用耗时记录结束："+invoker.getInterface()+"，\ttimestamp:"+(System.currentTimeMillis()-stamp));


        return result;
    }
    
    
    
}
