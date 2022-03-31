package com.sparta.homepage.service;

import com.sparta.homepage.dto.CommentsRequestDto;
import com.sparta.homepage.models.Comments;
import com.sparta.homepage.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

//    @Autowired
//    public CommentsService(CommentsRepository commentsRepository) {
//        this.commentsRepository = commentsRepository;
//    }
//
//    @Transactional // (id : 어떤 것을 업데이트 할건지, requestdto : 변경 시킬 내용)
//    public void update(Long Id, CommentsRequestDto requestDto){
//        Comments comments = commentsRepository.findById(Id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        comments.update(requestDto);
//    }

}
