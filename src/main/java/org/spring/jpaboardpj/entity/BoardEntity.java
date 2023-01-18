package org.spring.jpaboardpj.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.jpaboardpj.dto.BoardDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "board_jpa_tb")
@Builder
public class BoardEntity {

  @Id   //기본키
  @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 1증가
  @Column(name = "board_id")
  private Long id;

  @Column(nullable = false)
  private String title;
  @Column(nullable = false, length = 500)
  private String content;
  @Column(nullable = false)
  private String writer;

  @Column(columnDefinition = "integer default 0", nullable = false)
  private int hit;

  @Column(nullable = false)
  private String boardPw;  //board_pw

  @CreationTimestamp
  @Column(updatable = false)   // update -> 적용 X
  private LocalDateTime createTime; //create_time

  @UpdateTimestamp
  @Column(insertable = false)   // 처음 생성시 -> 적용 X
  private LocalDateTime updateTime;//update_time

  // Dto -> Entity
  // 추가-> 글등록
  // id, createTime, updateTime -> 자동 추가
  public static BoardEntity toBaordEntity(BoardDto boardDto) {
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setTitle(boardDto.getTitle());
    boardEntity.setContent(boardDto.getContent());
    boardEntity.setBoardPw(boardDto.getBoardPw());
    boardEntity.setHit(boardDto.getHit());
    boardEntity.setWriter(boardDto.getWriter());
    return boardEntity;
  }

  // Builder 적용
  public static  BoardEntity toBoardEntity(BoardDto boardDto){
    return BoardEntity.builder()
            .title(boardDto.getTitle())
            .content(boardDto.getContent())
            .writer(boardDto.getWriter())
            .boardPw(boardDto.getBoardPw())
            .hit(boardDto.getHit())
            .build();
  }



}
