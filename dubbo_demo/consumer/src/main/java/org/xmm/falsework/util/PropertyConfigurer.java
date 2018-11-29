//package org.xmm.falsework.util;
//
//import org.springframework.core.env.MutablePropertySources;
//import org.springframework.core.env.PropertyResolver;
//import org.springframework.core.env.PropertySourcesPropertyResolver;
//import org.springframework.core.io.support.ResourcePropertySource;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//
///**
// * 
// */
//public class PropertyConfigurer {
//
//    static String CEEHOO_HOME="${CEEHOO_HOME}";
//    static String CEEHOO_TEST_HOME="${CEEHOO_TEST_HOME}";
//
//    private  static  String  FILE_PEX="file:";
//
//
//
//    PropertyResolver propertyResolver;
//
//    MutablePropertySources propertySources;
//
//    public PropertyConfigurer(String... configs){
//        try {
//            propertySources = new MutablePropertySources();
//
//            for(String it:configs){
//                String fileconfig;
//                ResourcePropertySource resourcePropertySource = null;
//                if(it.contains(FILE_PEX)){
//                    fileconfig=evnFilePathParser(it);
//                    if(fileconfig != null)
//                        resourcePropertySource = new ResourcePropertySource(it, fileconfig);
//                    else
//                        System.out.println("config file :"+it+" not exists");
//                }
//                else
//                    resourcePropertySource = new ResourcePropertySource(it, it);
//                if(resourcePropertySource!=null)
//                    propertySources.addFirst(resourcePropertySource);
//                
//            }
//            propertyResolver = new PropertySourcesPropertyResolver(propertySources);
//        } catch (IOException e) {
//            System.err.println("配置文件加载错误。config file path："+ Arrays.asList(configs));
//            e.printStackTrace();
//            throw new RuntimeException("配置文件加载错误。config file path："+ Arrays.asList(configs));
//        }
//    }
//
//    public String getPropertyValue(String key) {
//        return propertyResolver.getProperty(key);
//    }
//
//
//    private String evnFilePathParser(String config){
//        String configpath="";
//        int startindex=config.indexOf("${");
//        int endindex=config.indexOf("}");
//        if(endindex<1) configpath=config;
//        String evnKey=config.substring(startindex+2,endindex);
//
//        String evnValue=System.getProperty(evnKey);
//        if(evnValue == null) {//如果为空，则读取环境变量配置
//            evnValue = System.getenv(evnKey);
//        }
//        if(evnValue!=null)
//            configpath= config.replace("${"+evnKey+"}",evnValue);
//        else
//            configpath=config;
//        File file=new File(configpath.substring(configpath.indexOf(FILE_PEX)+5));
//        if(file.exists()) return configpath;
//        else return null;
//
//    }
//
//
//}
