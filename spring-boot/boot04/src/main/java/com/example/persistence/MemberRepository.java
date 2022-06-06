package com.example.persistence;

import com.example.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    @Query("SELECT m.uid, COUNT(p) FROM Member m LEFT OUTER JOIN Profile p ON m.uid = p.member WHERE m.uid = ?1 GROUP BY m")
    List<Object[]> getMemberWithProfileCount(String uid);

    @Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p on m.uid = p.member WHERE m.uid = ?1 AND p.current = true")
    List<Object[]> getMemberWithProfile(String uid);
}
