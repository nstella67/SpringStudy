package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //다른 객체
    //MemberService()에 new MemoryMemberRepository()객체와 여기서 생성한 객체 new MemoryMemberRepository() 다르다
    //서로 다른 인스턴스
    //static이라 지금은 문제 없지만 문제가 생길 수 있다
    // 아래와 같이 바꾼다
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        // 테스트 끝날 때마다
        memberRepository.clearStore();    // 저장소 클리어
    }

    @Test
    void 회원가입() {   // 테스트는 한글로 적어도 됨
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    
    /*** 예외 중요 ***/
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));    //예외실패
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //예외성공
        // 메시지 검증? 밑에
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예외성공
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        
/*
테스트 실패
메시지 같으면 성공
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123455");
        }
*/

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}