package com.ly.common.utils;

public class TreadDemo1 {

    public static void main(String[] args) {
        // 方式一
//        MyThread thread =  new MyThread();
//         thread.start();

        // 方式二
        Thread thread =  new Thread(new MyThread2());
        thread.start();
    }

}

// 方式一 继承 Thread
class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i );
        }
    }
}

// 方式二 实现 Runnable 接口
class MyThread2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i );
        }
    }
}

