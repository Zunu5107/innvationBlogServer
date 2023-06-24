package com.innovation.level1.mapper;

import com.innovation.level1.dto.BoardPatchDto;
import com.innovation.level1.dto.BoardPostDto;
import com.innovation.level1.dto.BoardResponseDto;
import com.innovation.level1.entity.Board;
import org.mapstruct.Mapper;

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