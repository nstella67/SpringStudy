package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long Id); // Option : null 반환하는 경우도 있다고 가정 Optional 로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
