package com.example.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;


@SpringBootTest
@Slf4j
@Commit
class CustomCrudRepositoryImplTest {

    @Autowired
    CustomCrudRepository repository;

    @Test
    void test1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        String type = "t";
        String keyword = "10";

        Page<Object[]> result = repository.getCustomPage(type, keyword, pageable);

        log.info("{}", result);

        log.info("TOTAL PAGES: {}", result.getTotalPages());
        log.info("TOTAL SIZE: {}", result.getTotalElements());

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}