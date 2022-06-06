package com.example;

import com.example.domain.PDSBoard;
import com.example.domain.PDSFile;
import com.example.persistence.PDSBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
@Commit
public class PDSBoardTest {

    @Autowired
    PDSBoardRepository repository;

    @Test
    public void testInsertPDS() {
        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        PDSBoard pds = PDSBoard.builder()
                .pname("Document")
                .files(List.of(file1, file2))
                .build();

        log.info("try to save pds");

        repository.save(pds);
    }

    @Transactional
    @Test
    void updatePDSFileTest() {
        long fno = 1L;
        String newName = "updateFile1.doc";

        int count = repository.updatePDSFile(fno, newName);
        log.info("update count: " +  count);
    }

    @Transactional
    @Test
    void updatePDSFileTest2() {
        String newName = "updateFile2.doc";

        Optional<PDSBoard> result = repository.findById(3L);

        result.ifPresent(pds -> {
            log.info("데이터가 존재하므로 update 시도 ");
            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(newName);

            int idx = pds.getFiles().indexOf(target);

            if (idx > -1) {
                List<PDSFile> list = pds.getFiles();
                list.remove(idx);
                list.add(target);
                pds.setFiles(list);
            }
            repository.save(pds);
        });

    }

    @Transactional
    @Test
    void deletePDSFileTest() {
        Long fno = 2L;

        int count = repository.deletePDSFile(fno);

        log.info("DELETE PDSFILE: " + count);
    }

    @Test
    public void insertDummis() {

        List<PDSBoard> list = new ArrayList<>();

        IntStream.range(1, 100).forEach(i -> {
            PDSFile file1 = new PDSFile();
            file1.setPdsfile("file1.doc");

            PDSFile file2 = new PDSFile();
            file2.setPdsfile("file2.doc");

            PDSBoard pds = PDSBoard.builder()
                    .pname("자료" + i)
                    .files(List.of(file1, file2))
                    .build();

            log.info("try to save pds");

            list.add(pds);
        });

        repository.saveAll(list);
    }

    @Test
    void getSummaryTest() {
        repository.getSummary().forEach(arr -> log.info(Arrays.toString(arr)));
    }

}
