package com.example.crypto;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

public class AESTest {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("암호화할 문자열을 입력하세요: ");
        String plainText = scanner.nextLine();

        //비밀키 생성
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        Charset charset = Charset.forName("UTF-8");

        //암호화
        byte[] encryptData = encrypt(secretKey, plainText.getBytes(charset));
        System.out.println("암호화된 결과: " + bytesToHex(encryptData));

        //복호화
        byte[] decryptData = decrypt(secretKey, encryptData);
        System.out.println("복호화된 결과: " + new String(decryptData, charset));
    }

    private static byte[] encrypt(SecretKey secretKey, byte[] plaindata) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptData = cipher.doFinal(plaindata);
        return encryptData;
    }

    private static byte[] decrypt(SecretKey secretKey, byte[] encryptData) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptData = cipher.doFinal(encryptData);
        return decryptData;
    }

    static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }
}