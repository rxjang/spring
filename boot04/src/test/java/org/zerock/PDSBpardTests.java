package org.zerock;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.domain.PDSBoard;
import org.zerock.domain.PDSFile;
import org.zerock.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@SpringBootTest
@Log
class PDSBpardTests {

	@Autowired
	PDSBoardRepository repo;
	
	@Test
	public void tesInsertPDS() {
		
		PDSBoard pds = new PDSBoard();
		pds.setPname("Document");
		
		PDSFile file1 = new PDSFile();
		file1.setPdsfile("file1.doc");

		PDSFile file2 = new PDSFile();
		file2.setPdsfile("file2.doc");
		
		List<PDSFile> files = new ArrayList<>();
		files.add(file1);
		files.add(file2);
		
		pds.setFiles(files);
		
		log.info("try to save pds");
		
		repo.save(pds);
	}

}
