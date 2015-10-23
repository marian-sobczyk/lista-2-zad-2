package com.marian;

import java.util.concurrent.CountDownLatch;

public class Main {

    private static final byte[] cipher = CipherReader.readCipherFromFile("c.txt");
    private static final byte[] key = {0x8, 0xb, 0x5, 0xa, 0xe, 0x3, 0x7, 0x2, 0, 0, 0, 0, 0, 0, 0, 0};

    public static void main(String[] args) throws InterruptedException {
        Checker[] checkers = new Checker[8];
        CountDownLatch latch = new CountDownLatch(8);

        for (byte i = 0; i < 8; i++) {
            checkers[i] = new Checker(i * 2, i * 2 + 2, key, cipher, latch);
            checkers[i].start();
        }

        latch.await();
        System.out.println("Zakończono program");

    }

}
