package com.sparta.homepage.service;

import com.sparta.homepage.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;


//    @Transactional
//    public Long update(Long id, BoardRequestDto requestDto) {
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        board.update(requestDto);
//        return board.getId();
//    }
}
//    @Transactional // db에 꼭 넣어줘야해
// (id : 어떤 것을 업데이트 할건지, requestdto : 변경 시킬 내용)
//    public Long update(Long id, BoardRequestDto requestDto) {
//        // 업데이트에 필요한 컨텐츠를 찾기
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        board.update(requestDto);
//        return board.getId();
//    }