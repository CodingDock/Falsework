import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class main {


    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext.xml"});
        context.start();

        System.in.read();
        
        
    }
}
