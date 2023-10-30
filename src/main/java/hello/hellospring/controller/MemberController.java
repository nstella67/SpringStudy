package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    /*
    private final MemberService memberService = new MemberService();
    이렇게 new 로 생성해서 사용할 수 있다.
    그러나 이렇게 사용하면 MemberController 말고, 다른 여러 Controller들이 MemberService를 가져다 쓸 수 있는데, 여러 개 생성할 필요 없이 공용으로 쓰면 된다.
    */
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    /* Error
    Description:
    Parameter 0 of constructor in hello.hellospring.controller.MemberController required a bean of type 'hello.hellospring.service.MemberService' that could not be found.
    Action:
    Consider defining a bean of type 'hello.hellospring.service.MemberService' in your configuration.
    
    
    ->MemberService :: 순수한 Java 코드 이기 때문
    -> MemberService @Service, MemoryMemberRepository @Repository 해준다
     */
}
