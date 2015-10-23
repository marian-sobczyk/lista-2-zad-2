package com.marian;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by marian on 23.10.15.
 */
public class CipherReader {
    public static byte[] readCipherFromFile(String fileName) {
        try {
            String allText = getContentOfFile(fileName);

            return convertStringToBytes(allText);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read file " + fileName);
        } catch (IOException e) {
            System.out.println("Cannot read file " + fileName);
        }

        return new byte[0];
    }

    private static byte[] convertStringToBytes(String allText) {
        String[] strBytes = allText.split(" ");
        int counter = getLengthOfFullBytes(strBytes);
        return createBytes(strBytes, counter);
    }

    private static byte[] createBytes(String[] strBytes, int counter) {
        byte[] bytes = new byte[counter];
        int i = 0;
        int j;
        for (j = 0; j < strBytes.length; j++) {
            if (strBytes[j].length() == 8) {
                bytes[i] = (byte) Integer.parseInt(strBytes[j], 2);
                i++;
            }
        }
        return bytes;
    }

    private static int getLengthOfFullBytes(String[] strBytes) {
        int counter = 0;
        for (int i = 0; i < strBytes.length; i++) {
            if (strBytes[i].length() == 8) {
                counter++;
            }
        }
        return counter;
    }

    private static String getContentOfFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        return sb.toString();
    }
}
