package com.example.crypto;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.security.MessageDigest;

public class PassWordTest{

    public static void main(String[] args) {
        String password = "3108";
        String digest = password(password.getBytes());
        System.out.println("MySQL Password의 해시값 =" + digest);
        Scanner scanner = new Scanner(System.in);
        System.out.println("비밀번호를 입력하세요: ");
        String input = scanner.nextLine();
        System.out.println("내가 입력한 password의 해시값 = " + password(input.getBytes()));
        scanner.close();
        if (digest.equals(password(input.getBytes()))) {
            System.out.println("MySQL Password와 내가 입력한 password의 해시값이 일치합니다. 동일 비밀번호를 입력한 것으로 간주합니다. 로그인 성공");
         
        } else {
            System.out.println("MySQL Password와 내가 입력한 password의 해시값이 일치하지 않습니다. 다른 비밀번호를 입력한 것으로 간주합니다. 로그인 실패");
        }

    }
    public static byte[] getHash(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA1" + "Algorithm Not Found", e);
        }
    }

    static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // 각 바이트를 16진수 문자열로 변환하여 StringBuilder에 추가
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    public static String password(byte[] input) {
        byte[] digest = null;
        // Stage 1
        digest = getHash(input);
        return bytesToHex(digest);
    }
}