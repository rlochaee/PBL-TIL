package com.lion.PBL_W6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PblW6Application {

	public static void main(String[] args) {
		// 스프링 컨테이너를 실행하고 그 결과(Context)를 변수에 담습니다.
		ApplicationContext ac = SpringApplication.run(PblW6Application.class, args);

		// 컨테이너에서 MemberService 타입의 Bean을 꺼내옵니다.
		MemberService memberService = ac.getBean(MemberService.class);

		// 잘 가져왔는지 출력해봅니다.
		System.out.println("memberService = " + memberService);
	}
}