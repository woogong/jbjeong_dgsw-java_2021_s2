package kr.hs.dgsw.java.springStudy.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.hs.dgsw.java.springStudy.entity.WritingEntity;
import kr.hs.dgsw.java.springStudy.repository.WritingRepository;

@RestController
@RequestMapping(value = "/rest")
public class ApiController {

	@Autowired
	private WritingRepository writingRepository;

	@GetMapping(value = "/list")
	public List<WritingEntity> list() {
		return writingRepository.findAll();
	}

	@GetMapping(value = "/read/{idx}")
	public WritingEntity read(@PathVariable(name = "idx") int idx) {
		return writingRepository.findById(idx).get();
	}

	@PostMapping(value = "/insert")
	public WritingEntity insert(
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "writer") String writer) {
		
		WritingEntity writing = new WritingEntity();
		writing.setTitle(title);
		writing.setContent(content);
		writing.setWriter(writer);
		writing.setWriteTime(new Date());
		
		return writingRepository.save(writing);
	}

	@PostMapping(value = "/update")
	public WritingEntity update(
			@RequestParam(value = "idx") int idx,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "writer") String writer) {
		
		WritingEntity writing = writingRepository.findById(idx).get();
		writing.setTitle(title);
		writing.setContent(content);
		writing.setWriter(writer);
		
		return writingRepository.save(writing);
	}

	@PostMapping(value = "/delete/{idx}")
	public boolean update(@PathVariable(name = "idx") int idx) {
		writingRepository.deleteById(idx);
		
		return true;
	}
	

}
