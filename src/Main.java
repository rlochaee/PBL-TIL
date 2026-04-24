import role.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 아기사자 정보
        System.out.println("--- 아기사자 정보 입력 ---");
        System.out.print("이름: "); String lName = sc.nextLine();
        System.out.print("전공: "); String lMajor = sc.nextLine();
        System.out.print("기수: "); int lGen = sc.nextInt(); sc.nextLine();
        System.out.print("파트 (백엔드/프론트엔드): "); String lPart = sc.nextLine();
        System.out.print("학번: "); String lId = sc.nextLine();

        Person lion = new Lion(lName, lMajor, lGen, lPart, lId);

        // 운영진 정보
        System.out.println("\n--- 운영진 정보 입력 ---");
        System.out.print("이름: "); String sName = sc.nextLine();
        System.out.print("전공: "); String sMajor = sc.nextLine();
        System.out.print("기수: "); int sGen = sc.nextInt(); sc.nextLine();
        System.out.print("파트 (백엔드/프론트엔드/기획/디자인): "); String sPart = sc.nextLine();
        System.out.print("직책 (대표/부대표/파트장/멘토): "); String sRole = sc.nextLine();

        Person staff = new Staff(sName, sMajor, sGen, sPart, sRole);

        // 결과 출력
        System.out.println("\n--- 결과 출력 ---");
        display(lion);
        display(staff);
    }

    public static void display(Person p) {
        System.out.println(p.getDetails());
        boolean canSubmit = p.getPolicy().canSubmit();
        String status = canSubmit ? "✅ 가능" : "❌ 불가능";

        System.out.println("📝 과제 제출 가능 여부: " + status);
        System.out.println("----------------------------------");
    }
}