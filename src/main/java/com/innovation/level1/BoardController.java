package com.innovation.level1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

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
