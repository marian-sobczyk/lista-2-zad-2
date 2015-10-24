package com.marian;

import java.util.concurrent.CountDownLatch;

/**
 * Created by marian on 24.10.15.
 */
public class CheckerSemaphore {
    private final CountDownLatch latch;
    private final int count;

    public CheckerSemaphore(int value) {
        latch = new CountDownLatch(value);
        count = value;
    }

    public void await() throws InterruptedException {
        latch.await();
    }

    public void countDown() {
        latch.countDown();
    }

    public void done() {
        for (long i = 0; i < count; i++) {
            latch.countDown();
        }
    }
}
