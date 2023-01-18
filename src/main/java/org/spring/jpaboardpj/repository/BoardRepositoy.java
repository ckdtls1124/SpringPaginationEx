package org.spring.jpaboardpj.repository;

import org.spring.jpaboardpj.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepositoy extends JpaRepository<BoardEntity,Long> {

                      // BoardEntity
  @Modifying  // 수정
  @Query(value = " update BoardEntity b set b.hit=b.hit+1  where b.id=:id  ")
  void upHitCount(Long id);

}
