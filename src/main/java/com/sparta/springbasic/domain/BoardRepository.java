package com.sparta.springbasic.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// 인터페이스 -> 클래스에서 멤버변수가 없는 메소드만 있는 것
// <Board라는 클래스에, Id는 Long을 가져다 쓸거다>
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 쓰고 싶은 jpa 기능이 있으면 이름 규칙에 맞게 메소드명을 만듦
    List<Board> findAllByOrderByModifiedAtDesc();
}