package com.lion.PBL_W6.config;

import com.lion.PBL_W6.MemberService;
import com.lion.PBL_W6.MemoryMemberRepository;
import com.lion.PBL_W6.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration // 이 클래스를 스프링 설정 정보로 사용
public class AppConfig {

   /*@Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }*/
}