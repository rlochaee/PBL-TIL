package package2;

import package1.Lion;

public class Step3 {
    public static void main(String[] args) {
        Lion lion = new Lion("김멋대", "컴퓨터공학", 14);

        System.out.println("🔐 접근 제어자 테스트를 시작합니다.");

        lion.name = "홍길동";
        System.out.println("✅ public 필드(name) 수정 성공: " + lion.name);

        lion.major = "전자공학";

        lion.generation = 15;

        lion.printInfo();
    }
}