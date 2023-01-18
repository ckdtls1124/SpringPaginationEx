package org.spring.jpaboardpj.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.jpaboardpj.entity.BoardEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class BoardDto {

  private Long id;
  private String title;
  private String content;
  private String writer;
  private int hit=0;
  private String boardPw;  //board_pw
  private LocalDateTime createTime; //create_time
  private LocalDateTime updateTime;//update_time

  // Entity -> Dto
  // 추가-> 글등록
  // id, createTime, updateTime -> 자동 추가

  public static BoardDto toBaordto(BoardEntity boardEntity) {
    BoardDto boardDto = new BoardDto();
    boardDto.setTitle(boardEntity.getTitle());
    boardDto.setContent(boardEntity.getContent());
    boardDto.setBoardPw(boardEntity.getBoardPw());
    boardDto.setHit(boardEntity.getHit());
    boardDto.setWriter(boardEntity.getWriter());
    return boardDto;
  }

  // Builder 적용
  public static  BoardDto toGetBoardDto(BoardEntity boardEntity){
       return BoardDto.builder()
            .title(boardEntity.getTitle())
            .content(boardEntity.getContent())
            .writer(boardEntity.getWriter())
            .boardPw(boardEntity.getBoardPw())
            .hit(boardEntity.getHit())
            .build();
  }


  // get, 조회
  public static BoardDto toGeBaordListDo(BoardEntity boardEntity) {
    BoardDto boardDto = new BoardDto();
    boardDto.setId(boardEntity.getId());
    boardDto.setTitle(boardEntity.getTitle());
    boardDto.setContent(boardEntity.getContent());
    boardDto.setWriter(boardEntity.getWriter());
    boardDto.setBoardPw(boardEntity.getBoardPw());
    boardDto.setHit(boardEntity.getHit());
    boardDto.setCreateTime(boardEntity.getCreateTime());
    return boardDto;
  }


}
