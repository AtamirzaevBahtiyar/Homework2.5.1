package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread{

        private Semaphore sem;
        private int downloadSpeed;
        int id;
        int file;
        CountDownLatch countDownLatch;
        String downloading = "";


        public Downloaders (CountDownLatch countDownLatch, Semaphore sem, int downloadSpeed,int id){
                this.sem = sem;
                this.downloadSpeed = downloadSpeed;
                this.countDownLatch = countDownLatch;
                this.id = id;



        }


        public void run (){


                try {
                        countDownLatch.await();
                        sem.acquire();
                        System.out.println("Пользователь " + id + " скачивает файл");
                        for (int i = 0; i <500/downloadSpeed ; i++) {
                                file = file + downloadSpeed;
                                System.out.print(downloading + "⬇");
                                sleep(2000);
                        }
                        sem.release();

                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                System.out.println(" ");
                System.out.println("Пользователь " + id + " скачал файл");

        }
}
