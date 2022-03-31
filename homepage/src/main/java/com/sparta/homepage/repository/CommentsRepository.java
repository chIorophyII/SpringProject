package com.sparta.homepage.repository;

import com.sparta.homepage.models.Board;
import com.sparta.homepage.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    //코멘트에 저장해놓은 게시글 id를 이용해 조회
    List<Comments> findByBoardIdOrderByModifiedAtDesc(Long boardId);
}