package com.marian;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by marian on 23.10.15.
 */
public class Checker extends Thread {

    private final byte begin;
    private final byte end;
    private final byte[] key;
    private final byte[] cipher;
    private final CheckerSemaphore semaphore;

    public Checker(int begin, int end, byte[] key, byte[] cipher, CheckerSemaphore semaphore) {
        this.begin = (byte) begin;
        this.end = (byte) end;
        this.key = key;
        this.cipher = cipher;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        checkSolutions();
    }

    private void checkSolutions() {
        for (byte i0 = begin; i0 < end; i0++) {
            key[0] = getCharacterForNumber(i0);
            for (byte i1 = 0; i1 < 16; i1++) {
                key[1] = getCharacterForNumber(i1);
                for (byte i2 = 0; i2 < 16; i2++) {
                    key[2] = getCharacterForNumber(i2);
                    for (byte i3 = 0; i3 < 16; i3++) {
                        key[3] = getCharacterForNumber(i3);
                        for (byte i4 = 0; i4 < 16; i4++) {
                            key[4] = getCharacterForNumber(i4);
                            for (byte i5 = 0; i5 < 16; i5++) {
                                key[5] = getCharacterForNumber(i5);
                                for (byte i6 = 0; i6 < 16; i6++) {
                                    key[6] = getCharacterForNumber(i6);
                                    for (byte i7 = 0; i7 < 16; i7++) {
                                        key[7] = getCharacterForNumber(i7);
                                        checkSolution();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        semaphore.countDown();
    }

    private void checkSolution() {
        byte[] decoded = decode();
        boolean valid = MessageValidator.validate(decoded);
        if (valid) {
            MessagePrinter.printMessage("Key: ", key, " ");
            MessagePrinter.printMessage("Value: ", decoded, "");
            semaphore.done();
        }
    }

    private byte getCharacterForNumber(byte number) {
        if (number >= 0 && number <= 9) {
            return (byte) (number + 48);
        } else {
            return (byte) (number + 87);
        }
    }

    private byte[] decode() {
        SecretKeySpec secretKey = new SecretKeySpec(key, "RC4");
        RC4Engine engine = new RC4Engine();
        CipherParameters params = new KeyParameter(secretKey.getEncoded());
        engine.init(false, params);
        byte[] temp = new byte[cipher.length];
        engine.processBytes(cipher, 0, cipher.length, temp, 0);

        return temp;
    }
}
