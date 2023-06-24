package com.innovation.level1.service;

import com.innovation.level1.entity.Board;
import com.innovation.level1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board createBoard(Board board) {
        validateBoardData(board);

        return boardRepository.save(board);
    }


    public Board findBoard(long boardId) {

        return findVerifiedBoard(boardId);
    }


    public List<Board> findAllBoards() {

        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    public Board updateBoard(Board board) {
        Board findBoard = findVerifiedBoard(board.getBoardId());

        String inputPassword = board.getPassword();
        String password = findBoard.getPassword();

        comparePasswords(password, inputPassword);

        Optional.ofNullable(board.getTitle()).ifPresent(findBoard::setTitle);
        Optional.ofNullable(board.getContent()).ifPresent(findBoard::setContent);
        Optional.ofNullable(board.getUsername()).ifPresent(findBoard::setUsername);

        return boardRepository.save(findBoard);
    }


    public void deleteBoard(long boardId, String inputPassword) {
        Board findBoard = findVerifiedBoard(boardId);
        String password = findBoard.getPassword();

        comparePasswords(password, inputPassword);

        boardRepository.delete(findBoard);
    }


    private void validateBoardData(Board board) {
        Map<String, String> fields = new HashMap<String, String>() {{
            put("비밀번호", board.getPassword());
            put("이름", board.getUsername());
            put("제목", board.getTitle());
            put("내용", board.getContent());
        }};

        List<String> errors = new ArrayList<>();

        fields.forEach((key, value) -> {
            if (StringUtils.isEmpty(value)) {
                errors.add(key + "를 입력해주세요.");
            }
        });

        if (!errors.isEmpty()) {
            throw new RuntimeException(String.join("\n", errors));
        }
    }

    private void comparePasswords(String password, String inputPassword) {
        if(!password.equals(inputPassword))
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }

    private Board findVerifiedBoard(long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new RuntimeException("데이터가 존재하지 않습니다.")
        );
    }
}
