package com.marian;

import java.util.concurrent.CountDownLatch;

/**
 * Created by marian on 24.10.15.
 */
public class CheckerSemaphore {
    private final CountDownLatch latch;

    public CheckerSemaphore(int value) {
        latch = new CountDownLatch(value);
    }

    public void await() throws InterruptedException {
        latch.await();
    }

    public void countDown() {
        latch.countDown();
    }

    public void done() {
        long count = latch.getCount();
        for (long i = 0; i < count; i++) {
            latch.countDown();
        }
    }
}
