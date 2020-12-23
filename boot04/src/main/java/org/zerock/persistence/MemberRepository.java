package org.zerock.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

	@Query("Select m.uid, count(p) From Member m LEFT OUTER JOIN profile p on"
			+ "m.uid = p.member where m.uid = ?1 group by m")
	public List<Object[]> getMemberWithProfileCount(String uid);
}
