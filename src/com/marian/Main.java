package com.marian;

import java.util.concurrent.CountDownLatch;

public class Main {

    private static final byte[] cipher = CipherReader.readCipherFromFile("c.txt");
    private static final byte[] key = {0, 0, 0, 0, 0, 0, 0, 0, '8', 'b', '5', 'a', 'e', '3', '7', '2'};

    public static void main(String[] args) throws InterruptedException {
        Checker[] checkers = new Checker[8];
        CountDownLatch latch = new CountDownLatch(8);

        for (byte i = 0; i < 8; i++) {
            checkers[i] = new Checker(i * 2, i * 2 + 2, key.clone(), cipher.clone(), latch);
            checkers[i].start();
        }

        latch.await();
        System.out.println("Zakończono program");

    }

}
