package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x
        /**
        Optional<Member> result  = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            //result가 존재하면 동작함
            //Optional로 한번 감쌌기 때문에 null 체크를 하지 않고 존재 여부만 체크.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        //바로 꺼내고 싶으면
        result.get();
        //있으면 꺼내고 아니면 여기 있는 어떤 메서드를 실행해라
        result.orElseGet(메서드);
        */

        /**
        //Optional 바로 반환하는게 좋지 않기 때문에 하단 방법을 권장
        memberRepository.findByName(member.getName())
              .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
              });
        ->메서드로 Extract Method
        */
        
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
