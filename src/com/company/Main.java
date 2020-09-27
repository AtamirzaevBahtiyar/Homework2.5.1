package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Downloaders downloaders = null;

        new Uploader(500,20,countDownLatch).start();
        for (int i = 0; i < 10; i++) {
           downloaders = new Downloaders(countDownLatch, semaphore, 100,i+1);
           downloaders.start();
            try {
                downloaders.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Файл удален с сервера");
    }
}
