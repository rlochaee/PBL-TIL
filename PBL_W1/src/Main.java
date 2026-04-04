import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (true) {
            System.out.print("저장할 아기사자 수를 입력하세요 (5명 이상): ");
            count = sc.nextInt();
            sc.nextLine();

            if (count >= 5) {
                break;
            } else {
                System.out.println("[오류] 5 이상 입력해주세요.\n저장할 아기사자 수를 5 이상 입력해주세요.");
            }
        }
        System.out.println("아기사자 이름을 입력해주세요.");
        String[] names = new String[count];
        for (int i = 0; i < count; i++) {
            names[i] = sc.nextLine();
        }
        System.out.println("\n--- 아기사자 명단을 최종적으로 출력합니다 ---");
        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + ". " + names[i]);
        }
        System.out.println("명단 출력이 완료되었습니다. 프로그램을 종료합니다.");
        sc.close();
    }
}