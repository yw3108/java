package com.example.crypto;

import java.util.Random;
import java.util.Scanner;

public class RandomExample {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("시드 값을 입력하세요 (기본값: 0): ");
			long seed = scanner.nextLong();
			
			// 시드 값을 사용하여 Random 객체 생성
			Random random = new Random(seed);
			
			// 10개의 난수 생성
			System.out.println("생성된 난수:");
			for (int i = 0; i < 10; i++) {
			    int randomNumber = random.nextInt(100);
			    System.out.println(randomNumber);
			    //
			}
		}
    }
}

