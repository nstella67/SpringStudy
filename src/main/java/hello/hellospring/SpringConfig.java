package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

   @Bean
   public MemberService memberService() {
       return new MemberService(memberRepository());
   }    //MemberService를 스프링빈에 등록해라

    @Bean
    public MemberRepository memberRepository() {
       return new MemoryMemberRepository();
    }
    // MemberService, MemberRepository를 스프링빈에 등록하고
}
