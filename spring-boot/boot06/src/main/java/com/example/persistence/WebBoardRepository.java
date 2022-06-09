package com.example.persistence;

import com.example.domain.QWebBoard;
import com.example.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {

    @Query("SELECT b.bno, b.title, b.writer, b.regdate, count(r) " +
            "FROM WebBoard b " +
            "LEFT OUTER JOIN b.replies r WHERE b.bno > 0 GROUP BY b")
    List<Object[]> getListWithQuery(Pageable pageable);

    default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        QWebBoard board = QWebBoard.webBoard;

        // bno > 0
        builder.and(board.bno.gt(0));

        if (type == null) {
            return builder;
        }

        switch (type) {
            case "t":
                builder.and(board.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(board.content.like("%" + keyword + "%"));
                break;
            case "w":
                builder.and(board.writer.like("%" + keyword + "%"));
                break;
        }

        return builder;
    }
}
