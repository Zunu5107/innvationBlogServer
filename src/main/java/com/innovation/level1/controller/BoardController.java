package com.innovation.level1.controller;

import com.innovation.level1.dto.BoardDeleteDto;
import com.innovation.level1.dto.BoardPatchDto;
import com.innovation.level1.dto.BoardPostDto;
import com.innovation.level1.dto.BoardResponseDto;
import com.innovation.level1.entity.Board;
import com.innovation.level1.mapper.BoardMapper;
import com.innovation.level1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper boardMapper;


    @PostMapping("/board")
    public ResponseEntity<?> postBoard(@RequestBody BoardPostDto boardPostDto) {
        Board board = boardMapper.BoardPostDtoToBoard(boardPostDto);
        Board savedBoard = boardService.createBoard(board);

        BoardResponseDto response = boardMapper.BoardToBoardResponseDto(savedBoard);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable long boardId) {
        Board board = boardService.findBoard(boardId);

        BoardResponseDto response = boardMapper.BoardToBoardResponseDto(board);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/boards")
    public ResponseEntity<?> getAllBoards() {
        List<Board> boards = boardService.findAllBoards();

        List<BoardResponseDto> responses = boardMapper.BoardsToBoardResponseDtos(boards);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PatchMapping("/board/{boardId}")
    public ResponseEntity<?> patchBoard(@PathVariable long boardId,
                                        @RequestBody BoardPatchDto boardPatchDto) {
        boardPatchDto.setBoardId(boardId);
        Board board = boardMapper.BoardPatchDtoToBoard(boardPatchDto);
        Board patchedcBoard = boardService.updateBoard(board);

        BoardResponseDto response = boardMapper.BoardToBoardResponseDto(patchedcBoard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable long boardId,
                                         @RequestBody BoardDeleteDto boardDeleteDto) {

        String password = boardDeleteDto.getPassword();
        boardService.deleteBoard(boardId, password);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
