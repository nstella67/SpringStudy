package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /* 방법 1
    @Autowired DataSource dataSource;
    */

    /* 방법 2
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }   // 방법2 end
    */

//    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
       return new MemberService(memberRepository());
   }    //MemberService를 스프링빈에 등록해라

    @Bean
    public MemberRepository memberRepository() {
//       return new MemoryMemberRepository();
//       return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    // MemberService, MemberRepository를 스프링빈에 등록하고
}
