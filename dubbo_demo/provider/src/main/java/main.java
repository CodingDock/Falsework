import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class main {
    
    static Logger logger= LoggerFactory.getLogger(main.class);
    


    public static void main(String[] args) throws IOException {

        

    }
    
    
    static void startSpring() throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext.xml"});
        context.start();
        
        
        
        

        System.in.read();
        
    }

    
    static void log(){
        /**Logback动态修改日志级别
         LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
         loggerContext.getLogger("main").setLevel(Level.valueOf("TRACE"));
         */

        logger.trace("trace log...");
        logger.debug("debug log...");
        logger.info("info log...");
        logger.warn("warn log...");
    }

    
    
}
