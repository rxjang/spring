package org.zerock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.PDSBoard;
import org.zerock.domain.PDSFile;
import org.zerock.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@SpringBootTest
@Log
@Commit
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
	
	@Transactional
	@Test
	public void testUpdateFileName1() {
		Long fno = 1L;
		String newName= "updateFile1.doc";
		
		int count = repo.updatePDSFile(fno, newName);
		
		log.info("update count: "+ count);
	}
	
	@Test
	@Transactional
	public void testUpdateFileName2() {
		String newName = "updateFile2.doc";
		
		Optional<PDSBoard> result=repo.findById(2L);
		
		result.ifPresent(pds -> {
			log.info("데이터가 존재하므로 update 시도 ");
			
			PDSFile target = new PDSFile();
			target.setFno(2L);
			target.setPdsfile(newName);
			
			int idx = pds.getFiles().indexOf(target);
			
			if(idx > -1) {
				List<PDSFile> list = pds.getFiles();
				list.remove(idx);
				list.add(target);
				pds.setFiles(list);
			}
			repo.save(pds);
		});
	}
	
	@Transactional
	@Test
	public void deletePDSFile() {
		Long fno = 2L;
		
		int count = repo.deletePDSFile(fno);
		
		log.info("DELETE PDSFILE: " + count);
	}
	
	@Test
	public void insertDummies() {
		List<PDSBoard> list = new ArrayList<>();
		IntStream.range(1, 100).forEach(i ->{
			
			PDSBoard pds = new PDSBoard();
			pds.setPname("자료 " + i);
			
			PDSFile file1 = new PDSFile();
			file1.setPdsfile("file1.doc");

			PDSFile file2 = new PDSFile();
			file2.setPdsfile("file2.doc");
			
			List<PDSFile> files = new ArrayList<>();
			files.add(file1);
			files.add(file2);
			
			pds.setFiles(files);
			
			log.info("try to save pds");
			
			list.add(pds);
		});
		
		repo.saveAll(list);
	}
	
	@Test
	public void viewSummary() {
		repo.getSummary().forEach(arr->log.info(Arrays.toString(arr)));
	}

}
