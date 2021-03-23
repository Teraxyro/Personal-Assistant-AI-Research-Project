package com.company;


import com.company.AudioInput.Listen;

public class Main {

    public static boolean active = false;

    public static void main(String[] args) throws InterruptedException {
        Listen listen = new Listen();
        listen.start();
    }

}
