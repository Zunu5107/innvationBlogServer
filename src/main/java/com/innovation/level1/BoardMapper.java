package com.innovation.level1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
//    @Mapping(target = "boardId", ignore = true)
    Board BoardPostDtoToBoard(BoardPostDto post);
//    @Mapping(target = "boardId", ignore = true)
    Board BoardPatchDtoToBoard(BoardPatchDto patch);
    BoardResponseDto BoardToBoardResponseDto(Board board);
    List<BoardResponseDto> BoardsToBoardResponseDtos(List<Board> boards);
}
