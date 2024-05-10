package com.example.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class CipherModeExample {
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "1234567890123456".getBytes();
    private static final byte[] IV = new byte[16];

    static {
        new SecureRandom().nextBytes(IV);
    }

    public static void main(String[] args) {
        String plaintext = "융보공이최고야";
        byte[] plaintextBytes = plaintext.getBytes();

        // ECB Mode
        long ecbEncStartTime = System.nanoTime();
        byte[] ecbCiphertextBytes = encryptECB(plaintextBytes);
        long ecbEncEndTime = System.nanoTime();
        long ecbDecStartTime = System.nanoTime();
        byte[] ecbPlaintextBytes = decryptECB(ecbCiphertextBytes);
        long ecbDecEndTime = System.nanoTime();
        System.out.println("ECB Mode:");
        System.out.println("평문: " + new String(plaintextBytes));
        System.out.println("암호문: " + bytesToHex(ecbCiphertextBytes));
        System.out.println("복호화: " + new String(ecbPlaintextBytes));
        System.out.println("암호화에 걸린 시간: " + (ecbEncEndTime - ecbEncStartTime) + " ns");
        System.out.println("복호화에 걸린 시간: " + (ecbDecEndTime - ecbDecStartTime) + " ns");
        System.out.println();

        // CBC Mode
        long cbcEncStartTime = System.nanoTime();
        byte[] cbcCiphertextBytes = encryptCBC(plaintextBytes);
        long cbcEncEndTime = System.nanoTime();
        long cbcDecStartTime = System.nanoTime();
        byte[] cbcPlaintextBytes = decryptCBC(cbcCiphertextBytes);
        long cbcDecEndTime = System.nanoTime();
        System.out.println("CBC Mode:");
        System.out.println("평문: " + new String(plaintextBytes));
        System.out.println("암호문: " + bytesToHex(cbcCiphertextBytes));
        System.out.println("복호화: " + new String(cbcPlaintextBytes));
        System.out.println("암호화에 걸린시간: " + (cbcEncEndTime - cbcEncStartTime) + " ns");
        System.out.println("복호화에 걸린시간: " + (cbcDecEndTime - cbcDecStartTime) + " ns");
        System.out.println();

        // CFB Mode
        long cfbEncStartTime = System.nanoTime();
        byte[] cfbCiphertextBytes = encryptCFB(plaintextBytes);
        long cfbEncEndTime = System.nanoTime();
        long cfbDecStartTime = System.nanoTime();
        byte[] cfbPlaintextBytes = decryptCFB(cfbCiphertextBytes);
        long cfbDecEndTime = System.nanoTime();
        System.out.println("CFB Mode:");
        System.out.println("평문: " + new String(plaintextBytes));
        System.out.println("암호문: " + bytesToHex(cfbCiphertextBytes));
        System.out.println("복호화: " + new String(cfbPlaintextBytes));
        System.out.println("암호화에 걸린시간: " + (cfbEncEndTime - cfbEncStartTime) + " ns");
        System.out.println("복호화에 걸린시간: " + (cfbDecEndTime - cfbDecStartTime) + " ns");
        System.out.println();

        // OFB Mode
        long ofbEncStartTime = System.nanoTime();
        byte[] ofbCiphertextBytes = encryptOFB(plaintextBytes);
        long ofbEncEndTime = System.nanoTime();
        long ofbDecStartTime = System.nanoTime();
        byte[] ofbPlaintextBytes = decryptOFB(ofbCiphertextBytes);
        long ofbDecEndTime = System.nanoTime();
        System.out.println("OFB Mode:");
        System.out.println("평문: " + new String(plaintextBytes));
        System.out.println("암호문: " + bytesToHex(ofbCiphertextBytes));
        System.out.println("복호화: " + new String(ofbPlaintextBytes));
        System.out.println("암호화에 걸린시간: " + (ofbEncEndTime - ofbEncStartTime) + " ns");
        System.out.println("복호화에 걸린시간: " + (ofbDecEndTime - ofbDecStartTime) + " ns");
        System.out.println();

        // CTR Mode
        long ctrEncStartTime = System.nanoTime();
        byte[] ctrCiphertextBytes = encryptCTR(plaintextBytes);
        long ctrEncEndTime = System.nanoTime();
        long ctrDecStartTime = System.nanoTime();
        byte[] ctrPlaintextBytes = decryptCTR(ctrCiphertextBytes);
        long ctrDecEndTime = System.nanoTime();
        System.out.println("CTR Mode:");
        System.out.println("평문: " + new String(plaintextBytes));
        System.out.println("암호문: " + bytesToHex(ctrCiphertextBytes));
        System.out.println("복호화: " + new String(ctrPlaintextBytes));
        System.out.println("암호화에 걸린시간: " + (ctrEncEndTime - ctrEncStartTime) + " ns");
        System.out.println("복호화에 걸린시간: " + (ctrDecEndTime - ctrDecStartTime) + " ns");
        System.out.println("암호화 시간 비교:");
        System.out.println("ECB: " + (ecbEncEndTime - ecbEncStartTime) + " ns");
        System.out.println("CBC: " + (cbcEncEndTime - cbcEncStartTime) + " ns");
        System.out.println("CFB: " + (cfbEncEndTime - cfbEncStartTime) + " ns");
        System.out.println("OFB: " + (ofbEncEndTime - ofbEncStartTime) + " ns");
        System.out.println("CTR: " + (ctrEncEndTime - ctrEncStartTime) + " ns");

        long[] encryptionTimes = {ecbEncEndTime - ecbEncStartTime, cbcEncEndTime - cbcEncStartTime,
                cfbEncEndTime - cfbEncStartTime, ofbEncEndTime - ofbEncStartTime, ctrEncEndTime - ctrEncStartTime};
        System.out.println("암호화 시간 순위: " + orderTimes(encryptionTimes));

        System.out.println("\n복호화 시간 비교:");
        System.out.println("ECB: " + (ecbDecEndTime - ecbDecStartTime) + " ns");
        System.out.println("CBC: " + (cbcDecEndTime - cbcDecStartTime) + " ns");
        System.out.println("CFB: " + (cfbDecEndTime - cfbDecStartTime) + " ns");
        System.out.println("OFB: " + (ofbDecEndTime - ofbDecStartTime) + " ns");
        System.out.println("CTR: " + (ctrDecEndTime - ctrDecStartTime) + " ns");

        long[] decryptionTimes = {ecbDecEndTime - ecbDecStartTime, cbcDecEndTime - cbcDecStartTime,
                cfbDecEndTime - cfbDecStartTime, ofbDecEndTime - ofbDecStartTime, ctrDecEndTime - ctrDecStartTime};
        System.out.println("복호화 시간 순위: " + orderTimes(decryptionTimes));
    }
    
    

    // ECB 모드 암호화 메서드
    private static byte[] encryptECB(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ECB 모드 복호화 메서드
    private static byte[] decryptECB(byte[] ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CBC 모드 암호화 메서드
    private static byte[] encryptCBC(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CBC 모드 복호화 메서드
    private static byte[] decryptCBC(byte[] ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CFB 모드 암호화 메서드
    private static byte[] encryptCFB(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CFB 모드 복호화 메서드
    private static byte[] decryptCFB(byte[] ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // OFB 모드 암호화 메서드
    private static byte[] encryptOFB(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // OFB 모드 복호화 메서드
    private static byte[] decryptOFB(byte[] ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CTR 모드 암호화 메서드
    private static byte[] encryptCTR(byte[] plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CTR 모드 복호화 메서드
    private static byte[] decryptCTR(byte[] ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHex(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    private static String orderTimes(long[] times) {
        long[] sortedTimes = times.clone();
        Arrays.sort(sortedTimes);

        StringBuilder sb = new StringBuilder();
        for (long time : sortedTimes) {
            int index = findIndex(times, time);
            switch (index) {
                case 0:
                    sb.append("ECB < ");
                    break;
                case 1:
                    sb.append("CBC < ");
                    break;
                case 2:
                    sb.append("CFB < ");
                    break;
                case 3:
                    sb.append("OFB < ");
                    break;
                case 4:
                    sb.append("CTR < ");
                    break;
            }
        }
        return sb.toString();
    }

    private static int findIndex(long[] arr, long value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    
    
}