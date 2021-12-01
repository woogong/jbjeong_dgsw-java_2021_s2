package kr.hs.dgsw.java.springStudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hs.dgsw.java.springStudy.entity.WritingEntity;
import kr.hs.dgsw.java.springStudy.repository.WritingRepository;

@RestController
@RequestMapping(value = "/rest")
public class ApiController {

	@Autowired
	private WritingRepository writingRepository;
	
	@GetMapping(value = "/list")
	public List<WritingEntity> getName() {
		/*
		WritingEntity writing = new WritingEntity();
		writing.setIdx(100);
		writing.setTitle("기본제목");
		writing.setContent("내용입니다");
		*/
		
		// DB에서 idx가 12인 글을 읽어와 리턴해 볼께요.
		List<WritingEntity> writings = writingRepository.findAll();
		
		
		
		return writings;
	}
	
	@GetMapping(value = "/read")
	public WritingEntity read() {
		/*
		WritingEntity writing = new WritingEntity();
		writing.setIdx(100);
		writing.setTitle("기본제목");
		writing.setContent("내용입니다");
		*/
		
		// DB에서 idx가 12인 글을 읽어와 리턴해 볼께요.
		WritingEntity writing = writingRepository.findById(12).get();
		
		
		return writing;
	}

	
}
