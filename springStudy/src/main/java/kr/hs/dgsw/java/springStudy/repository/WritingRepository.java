package kr.hs.dgsw.java.springStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hs.dgsw.java.springStudy.entity.WritingEntity;

public interface WritingRepository extends JpaRepository<WritingEntity, Integer> {

	
}
