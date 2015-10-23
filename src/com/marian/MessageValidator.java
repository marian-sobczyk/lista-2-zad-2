package com.marian;

/**
 * Created by marian on 23.10.15.
 */
public class MessageValidator {
    public static boolean validate(byte[] decoded) {
        boolean valid = true;
        for (int i = 0; i < decoded.length && valid; i++) {
            byte character = decoded[i];
            if (character == 32 || (character >= 40 && character <= 125)) {
                valid = true;
            } else {
                valid = false;
            }
        }

        return valid;
    }
}
