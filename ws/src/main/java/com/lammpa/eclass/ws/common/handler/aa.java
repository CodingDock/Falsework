package com.lammpa.eclass.ws.common.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class aa {

    public static void main(String[] args) {

        TTT t=new TTT();
//        run t=new run();
        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        t.start();
        
        
//        new Thread(t).start();
//        new Thread(t).start();


//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//        fixedThreadPool.execute(t);
        
        
    }
    
}


class TTT extends Thread{

    private int it=10;

    @Override
    public void run() {
        
        for(int i=0;i<10;i++){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            it--;
            System.out.println(it);
        }
        
    }

}


class run implements Runnable{


    private int it=10;

    @Override
    public void run() {

        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            it--;
            System.out.println(it);
        }

    }
}