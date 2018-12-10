package org.xmm.falsework.xmm.util.log;

import org.slf4j.MDC;

import java.util.UUID;

public class MDCUtils {
    public static String get(String key) {
        return MDC.get(key);
    }

    /**
     * 如果MDC中不包含key，则返回defaultValue 
     * @param key
     * @param defaultValue
     * @return
     */
    public static String get(String key,String defaultValue) {
        String value = MDC.get(key);
        return value == null ? defaultValue : value;
    }

    public static String getRequestId() {
        return MDC.get(MDCConstants.REQUEST_ID_MDC_KEY);
    }

    public static String getRequestSeq() {
        return MDC.get(MDCConstants.REQUEST_SEQ_MDC_KEY);
    }

    public static String getUri() {
        return MDC.get(MDCConstants.URI_MDC_KEY);
    }

    /**
     * 获取此请求进入MDCFilter的时间戳 
     * @return
     */
    public static String getTimestampOfFilter() {
        return MDC.get(MDCConstants.TIMESTAMP);
    }

    /**
     * 获取当前server的本地IP 
     * @return
     */
    public static String getLocalIp() {
        return MDC.get(MDCConstants.LOCAL_IP_MDC_KEY);
    }

    public static String nextRequestSeq() {
        return MDC.get(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY);
    }

    public static String getHeader(String header) {
        return MDC.get(MDCConstants.HEADER_KEY_PREFIX + header);
    }

    public static String getCookie(String name) {
        return MDC.get(MDCConstants.COOKIE_KEY_PREFIX + name);
    }

    public static String getParameter(String name) {
        return MDC.get(MDCConstants.PARAMETER_KEY_PREFIX + name);
    }

    //如果你手动设置了MDC的值，请你要么配置HttpRequestMDCFilter，要么就是自己在合适的地方执行clear()方法  
    public static void put(String key,String value) {
        MDC.put(key,value);
    }

    public static void clear() {
        MDC.clear();
    }

    public static void remove(String key) {
        MDC.remove(key);
    }

    /**
     * 处理 {@code MDCConstants.REQUEST_ID_HEADER},
     *      {@code MDCConstants.REQUEST_SEQ_HEADER},
     *      {@code MDCConstants.REQUEST_ID_MDC_KEY},
     *      {@code MDCConstants.REQUEST_SEQ_MDC_KEY},
     *      {@code MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY}
     *     这五个字段.
     */
    public static void RidAndRseqHandle(){
        String request_id_header = MDC.get(MDCConstants.REQUEST_ID_HEADER);
        String request_seq_header = MDC.get(MDCConstants.REQUEST_SEQ_HEADER);
        
        if(request_id_header == null && request_seq_header == null){//父级请求，由web或app发起
            MDC.put(MDCConstants.REQUEST_ID_MDC_KEY, UUID.randomUUID().toString());
            MDC.put(MDCConstants.REQUEST_SEQ_MDC_KEY, "0");
            MDC.put(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY, "00");
        }else{//子http请求，服务器之间的http调用，由父级请求发起,
            MDC.put(MDCConstants.REQUEST_ID_MDC_KEY, request_id_header);
            MDC.put(MDCConstants.REQUEST_SEQ_MDC_KEY, request_seq_header+"0");
            MDC.put(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY, request_seq_header+"00");
        }
    }
    
    
    public static void subHttpRequestHandle(){
        
        
        
        
    }
    
}
