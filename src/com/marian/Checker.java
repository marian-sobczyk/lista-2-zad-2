package com.marian;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.spec.SecretKeySpec;
import java.util.concurrent.CountDownLatch;

/**
 * Created by marian on 23.10.15.
 */
public class Checker extends Thread {

    private final byte begin;
    private final byte end;
    private final byte[] key;
    private final byte[] cipher;
    private final CountDownLatch latch;

    public Checker(int begin, int end, byte[] key, byte[] cipher, CountDownLatch latch) {
        this.begin = (byte) begin;
        this.end = (byte) end;
        this.key = key;
        this.cipher = cipher;
        this.latch = latch;
    }


    @Override
    public void run() {
        checkSolutions();
    }

    private void checkSolutions() {
        for (byte i0 = begin; i0 < end; i0++) {
            System.out.println("Wątek " + begin / 2 + " zaczyna nową pętlę główną");
            key[0] = i0;
            for (byte i1 = 0; i1 < 16; i1++) {
                key[1] = i1;
                for (byte i2= 0; i2 < 16; i2++) {
                    key[2] = i2;
                    for (byte i3 = 0; i3 < 16; i3++) {
                        key[3] = i3;
                        for (byte i4 = 0; i4 < 16; i4++) {
                            key[4] = i4;
                            for (byte i5 = 0; i5 < 16; i5++) {
                                key[5] = i5;
                                for (byte i6 = 0; i6 < 16; i6++) {
                                    key[6] = i6;
                                    for (byte i7 = 0; i7 < 16; i7++) {
                                        key[7] = i7;
                                        byte[] decoded = decode();
                                        boolean valid = MessageValidator.validate(decoded);
                                        if (valid) {
                                            MessagePrinter.printMessage("Key: ", key, " ");
                                            MessagePrinter.printMessage("Value: ", decoded, "");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }



    private byte[] decode() {
        SecretKeySpec secretKey = new SecretKeySpec(key,"RC4");
        RC4Engine engine = new RC4Engine();
        CipherParameters params = new KeyParameter(secretKey.getEncoded());
        engine.init(true, params);
        byte[] temp=new byte[cipher.length];
        engine.processBytes(cipher, 0, cipher.length, temp, 0);

        return temp;
    }
}
