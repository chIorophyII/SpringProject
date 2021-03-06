package com.sparta.homepage.models;


import com.sparta.homepage.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@EntityListeners(AuditingEntityListener.class)
public class Board extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // nullable = false 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

//    // 여러 댓글
//    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    List<Comments> comments = new ArrayList<>();

    public Board(String title, String username, String contents) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }

    // 게시글 생성
    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.username = boardRequestDto.getUsername();
        this.contents = boardRequestDto.getContents();
    }

//    // 게시글 수정
//    public void update(BoardRequestDto requestDto) {
//        this.title = requestDto.getTitle();
//        this.username = requestDto.getUsername();
//        this.contents = requestDto.getContents();
//    }
//
//    public void update(BoardRequestDto requestDto) {
//    }

//    public Board(BoardRequestDto requestDto) {
//        super();
}


