package org.xmm.falsework.xmm;

import lombok.extern.slf4j.Slf4j;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{
    private static int _genmachine = 0;
    
    public static void getOne(){

            try {
                // build a 2-byte machine piece based on NICs info
                int machinePiece;
                {
                    try {
                        StringBuilder sb = new StringBuilder();
                        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
                        while ( e.hasMoreElements() ) {
                            NetworkInterface ni = e.nextElement();
                            sb.append(ni.toString());
                        }
                        machinePiece = sb.toString().hashCode() << 16;
                    } catch ( Throwable e ) {
                        // exception sometimes happens with IBM JVM, use random
                        log.error(" IdWorker error. ", e);
                        machinePiece = new Random().nextInt() << 16;
                    }
                    log.debug("machine piece post: " + Integer.toHexString(machinePiece));
                }

                // add a 2 byte process piece. It must represent not only the JVM
                // but the class loader.
                // Since static var belong to class loader there could be collisions
                // otherwise
                final int processPiece;
                {
                    int processId = new Random().nextInt();
                    try {
                        processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();
                    } catch ( Throwable t ) {}

                    ClassLoader loader = App.class.getClassLoader();
                    int loaderId = loader != null ? System.identityHashCode(loader) : 0;

                    StringBuilder sb = new StringBuilder();
                    sb.append(Integer.toHexString(processId));
                    sb.append(Integer.toHexString(loaderId));
                    processPiece = sb.toString().hashCode() & 0xFFFF;
                    log.debug("process piece: " + Integer.toHexString(processPiece));
                }

                _genmachine = machinePiece | processPiece;
                log.debug("machine : " + Integer.toHexString(_genmachine));

                System.out.println(_genmachine);
            } catch ( Exception e ) {
                throw new RuntimeException(e);
            }

    }

    public static void main( String[] args ){
        
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(10)*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getOne();
                }
            }).start();
            
        }
        
    }
    
}
