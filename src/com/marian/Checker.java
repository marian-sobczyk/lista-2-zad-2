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
        for (byte i8 = begin; i8 <= end; i8++) {
            System.out.println(i8);
            key[8] = i8;
            for (byte i9 = 0; i9 <= 16; i9++) {
                key[9] = i9;
                for (byte i10= 0; i10 <= 16; i10++) {
                    key[10] = i10;
                    for (byte i11 = 0; i11 <= 16; i11++) {
                        key[11] = i11;
                        for (byte i12 = 0; i12 <= 16; i12++) {
                            key[12] = i12;
                            for (byte i13 = 0; i13 <= 16; i13++) {
                                key[13] = i13;
                                for (byte i14 = 0; i14 <= 16; i14++) {
                                    key[14] = i14;
                                    for (byte i15 = 0; i15 <= 16; i15++) {
                                        key[15] = i15;
                                        byte[] decoded = decode();
                                        boolean valid = MessageValidator.validate(decoded);
                                        if (valid) {
                                            MessagePrinter.printMessage("Key: ", key);
                                            MessagePrinter.printMessage("Value: ", decoded);
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
