package com.sparta.springbasic.service;

import com.sparta.springbasic.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor // final로 생성한게 있으면 생성할 때 무조건 넣어줄게
@Service // 스프링한테 sevice 인거 알려주기
public class BoardService {
    // final -> 꼭 필요하다
    private final BoardRepository boardRepository;


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
}
