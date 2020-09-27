package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread{

    private int file;
    private int downloadSpeed = 20;
    private CountDownLatch cdl;
    String uploading = "";

    public Uploader (int file, int downloadSpeed,CountDownLatch cdl){
        this.file = file;
        this.downloadSpeed = downloadSpeed;
        this.cdl = cdl;
    }


    @Override
    public void run() {
        super.run();
        System.out.println("Загружает на сервер");

        for (int i = 0; i <file/downloadSpeed; i++) {
            try {
                sleep(100);;
                System.out.print(uploading + " ➡ ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(" ");
        System.out.println("Загрузил файл на сервер");
        cdl.countDown();
    }
}

