package org.spring.jpaboardpj.service;

import lombok.RequiredArgsConstructor;
import org.spring.jpaboardpj.dto.BoardDto;
import org.spring.jpaboardpj.entity.BoardEntity;
import org.spring.jpaboardpj.repository.BoardRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
  //1.
 /* @Autowired
  private BoardRepositoy boardRepositoy;*/
  //2.
/*  private  BoardRepositoy boardRepositoy;
  private BoardService(BoardRepositoy boardRepositoy){
    this.boardRepositoy=boardRepositoy;
  }*/
  //3.
  private final  BoardRepositoy boardRepositoy;

  @Transactional
  public void insertBoard(BoardDto boardDto) {
      BoardEntity boardEntity=BoardEntity.toBaordEntity(boardDto);
      boardRepositoy.save(boardEntity);

  }
  public List<BoardDto> boardListDo() {
    List<BoardDto> boardDtoList=new ArrayList<>();
    List<BoardEntity> boardEntityList=boardRepositoy.findAll();

    for(BoardEntity boardEntity: boardEntityList){
      boardDtoList.add(BoardDto.toGeBaordListDo(boardEntity));
    }
    return boardDtoList;
  }
  // hit수 증가
  @Transactional
  public void upHit(Long id) {
    // 직접 SQL작성 Repository
    boardRepositoy.upHitCount(id);
  }
  @Transactional  // insert, update ,delete
  public BoardDto boardDetail(Long id) {
     Optional<BoardEntity> optionalBoardEntity=boardRepositoy.findById(id);
    if (optionalBoardEntity.isPresent()) {
      // 있으면  -> Dto변환
/*      BoardDto boardDto=BoardDto.toGetBoardDto(optionalBoardEntity.get());// 레코드 get
      return boardDto;  */
      return BoardDto.toGeBaordListDo(optionalBoardEntity.get());// 레코드 get
    }else{
    // 없으면  -> null 변화
      return null;
    }
  }

  public Page<BoardDto> boardPagingList(Pageable pageable) {
    // DB -> Entity                    @PageableDefault(page = 0,size = 3,sort = "id",
    //                                  direction = Sort.Direction.DESC
    Page<BoardEntity> boardEntityList = boardRepositoy.findAll(pageable); //1 DB 데이터 get
                                      // Paging List  Entity-> Dto
    Page<BoardDto> boardDtoList = boardEntityList.map(BoardDto::toGeBaordListDo);

    return boardDtoList; //List Dto
  }




















  public Page<BoardDto> boardPagingList1(Pageable pageable) {
    Page<BoardEntity> result=boardRepositoy.findAll(pageable);
    Page<BoardDto> dto=result.map(BoardDto::toGetBoardDto);
    return dto;
  }
}











