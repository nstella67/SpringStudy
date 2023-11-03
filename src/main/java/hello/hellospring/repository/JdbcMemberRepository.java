package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
//        dataSource.getConnection();
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";  // 변수보다 밖에 상수로 꺼내는게 낫다

        Connection conn = dataSource.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getName());

        pstmt.executeUpdate();

        return null;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
