package package2;

import java.util.*;
import role.*;
import policy.SubmissionPolicy;

public class Main {
    private static MemberService service;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository (더미 데이터)");
        System.out.print("선택: ");

        int repoChoice = sc.nextInt();
        sc.nextLine();

        MemberRepository repository;
        if (repoChoice == 1) {
            repository = new MemoryMemberRepository();
        } else {
            repository = new MockMemberRepository();
        }

        service = new MemberService(repository);

        while (true) {
            System.out.println("\n======= 🦁 멤버 관리 시스템 (Step 2: DI 적용)=======");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    showAllMembers();
                    break;
                case 3:
                    searchByName();
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addMember() {
        System.out.println("\n--- 📝 멤버 등록 ---");
        System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
        int roleType = sc.nextInt();
        sc.nextLine();

        System.out.print("👤 이름: ");
        String name = sc.nextLine();

        System.out.print("🎓 전공: ");
        String major = sc.nextLine();

        System.out.print("📌 기수: ");
        int generation = sc.nextInt();
        sc.nextLine();

        System.out.print("💻 파트 (백엔드/프론트엔드/기획/디자인): ");
        String part = sc.nextLine();

        Role newMember;
        if (roleType == 1) {
            System.out.print("🆔 학번: ");
            String studentId = sc.nextLine();
            newMember = new Lion(name, major, generation, part, studentId);
        } else {
            System.out.print("💼 직책: ");
            String roleDescription = sc.nextLine();
            newMember = new Staff(name, major, generation, part, roleDescription);
        }

        if (service.join(newMember)) {
            System.out.println("✅ 등록 완료: " + name);
        } else {
            System.out.println("❌ 등록 실패: 이미 존재하는 이름입니다.");
        }
    }

    private static void showAllMembers() {
        System.out.println("\n--- 📋 전체 멤버 목록 ---");
        List<Role> members = service.findMembers();

        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        for (int i = 0; i < members.size(); i++) {
            Role member = members.get(i);
            String roleName = (member instanceof Lion) ? "아기사자" : "운영진";

            System.out.printf("%d. [%s] %s - %d기\n",
                    (i + 1), roleName, member.getName(), member.getGeneration());
        }
        System.out.println("\n📊 총 " + service.getTotalCount() + "명");
    }

    private static void searchByName() {
        System.out.println("\n--- 🔍 이름으로 검색 ---");
        System.out.print("검색할 이름: ");
        String searchName = sc.nextLine();

        Role member = service.findOne(searchName);

        if (member != null) {
            System.out.println("\n✨ [검색 결과]");
            System.out.println(member.getDetails());

            SubmissionPolicy policy = member.getPolicy();
            boolean canSubmit = policy.canSubmit();

            String status = canSubmit ? "✅ 가능" : "❌ 불가능";
            System.out.println("📝 과제 제출 가능 여부: " + status);
        } else {
            System.out.println("❌ 해당 이름의 멤버를 찾을 수 없습니다.");
        }
    }
}