package com.example.crypto;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class BouncyCastleTest {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        Provider provider = Security.getProvider("BC"); //BC는 Bouncy Castle의 약자
        if (provider != null) {
            System.out.println("Bouncy Castle provider가 사용 가능합니다.");
        } else {
            System.out.println("Bouncy Castle provider가 사용 불가합니다.");
        }
    }

}
