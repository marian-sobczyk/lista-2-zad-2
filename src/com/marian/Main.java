package com.marian;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.JCEStreamCipher.RC4;

public class Main {

    private static final byte[] cipher = CipherReader.readCipherFromFile("c.txt");

    public static void main(String[] args) {
    }
}
