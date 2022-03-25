package com.sparta.springbasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
@EntityListeners(AuditingEntityListener.class)
public class Board extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    private Long id;

    // 제목
    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;

    // 작성자명
    @Column(nullable = false)
    private String username;

    // 내용
    @Column(nullable = false)
    private String contents;

    // 생성자
    public Board(String title, String username, String contents) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
