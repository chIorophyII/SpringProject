package com.sparta.homepage.models;

import com.sparta.homepage.dto.BoardRequestDto;
import com.sparta.homepage.dto.CommentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@EntityListeners(AuditingEntityListener.class)
public class Comments extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="comment_id")
    private Long id;

//    // nullable = false 반드시 값을 가지도록 합니다.
//    // 한 작성자
//    @ManyToOne
//    @JoinColumn(name = "USER_ID", nullable = false)
//    private User user;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long boardId;

//    @Column(nullable = false)
//    private Long userId;

//    // 한 게시풀
//    @ManyToOne
//    @JoinColumn(name = "BOARD_ID")
//    private Board board;

    // 댓글 생성
    public Comments(CommentsRequestDto commentsRequestDto, String username) {
        this.username = username;
        this.contents = commentsRequestDto.getContents();
        this.boardId = commentsRequestDto.getBoardId();
    }


//    // 댓글 수정
//    public void update(CommentsRequestDto commentsRequestDto) {
//        this.contents = commentsRequestDto.getContents();
//    }
}
