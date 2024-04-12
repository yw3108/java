package com.example.hello;

public class JavaBasics {
    public static void main(String[] args) {
        // 변수와 자료형
        int age = 25;
        String name = "John";
        System.out.println("이름: " + name + ", 나이: " + age);

        // 조건문 (if-else)
        int score = 85;
        if (score >= 90) {
            System.out.println("A학점");
        } else if (score >= 80) {
            System.out.println("B학점");
        } else {
            System.out.println("C학점 이하");
        }

        // 반복문 (for)
        System.out.println("for문 예시:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }

        // 반복문 (while)
        System.out.println("while문 예시:");
        int i = 1;
        while (i <= 5) {
            System.out.println(i);
            i++;
        }
        // 메소드 호출
        int result = add(3, 5);
        System.out.println("3 + 5 = " + result);

        // 객체 생성 및 메소드 호출
        Car myCar = new Car("red", 0);
        System.out.println("차량 색상: " + myCar.color);
        System.out.println("초기 속도: " + myCar.speed);
        myCar.accelerate();
        System.out.println("가속 후 속도: " + myCar.speed);
    }

    // 메소드 정의
    public static int add(int a, int b) {
        return a + b;
    }
}

// 클래스 정의
class Car {
    // 필드
    String color;
    int speed;

    // 생성자
    public Car(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    // 메소드
    public void accelerate() {
        speed += 10;
    }
}
        
        