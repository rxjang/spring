package org.zerock.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.domain.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

}
