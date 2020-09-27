package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static int quantityOfDownloaders = 10;

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch cdl = new CountDownLatch(quantityOfDownloaders);

        new Uploader(500,20,countDownLatch).start();
        for (int i = 0; i < quantityOfDownloaders; i++) {
           new Downloaders(countDownLatch, semaphore, 100,i+1,cdl).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Файл удален с сервера");
    }
}
