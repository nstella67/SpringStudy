package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    /*
    private final MemberService memberService = new MemberService();
    이렇게 new 로 생성해서 사용할 수 있다.
    그러나 이렇게 사용하면 MemberController 말고, 다른 여러 Controller들이 MemberService를 가져다 쓸 수 있는데, 여러 개 생성할 필요 없이 공용으로 쓰면 된다.
    */

//    @Autowired private MemberService memberService;   // 필드주입. 별로 안좋다. 바꿀 수 있는 방법이 없다.
    private final MemberService memberService;

    /**
     * setter 주입. setter를 통해 주입
     * 단점 public하게 노출됨
     private MemberService memberService;

     @Autowired
     public MemberController(MemberService memberService) {
        this.memberService = memberService;
     }
     */

    // 생성자 주입. 생성자를 통해서 들어오는 것
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

    // 조회할때
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 데이터를 전달할때
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
}
