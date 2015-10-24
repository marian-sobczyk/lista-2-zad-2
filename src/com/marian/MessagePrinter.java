package com.marian;

/**
 * Created by marian on 23.10.15.
 */
public class MessagePrinter {
    public static void printMessage(String s, byte[] decoded, String breaker) {
        System.out.println(s);
        for (byte aDecoded : decoded) {
            System.out.print((char) aDecoded);
            System.out.print(breaker);
        }
        System.out.println();
    }
}
