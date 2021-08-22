package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id//primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터 사용시
    private String content; //섬머노트 라이브러리 쓸거라 <html>태그가 섞여들어가서 data가 많이 필요함.

    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER)//Board가 Many고 User가 one이다.
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다.(난 FK가 아니에요) DB에 컬럼을 만들지 마세요.\
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")//replies를 내림차순으로 정렬
    private List<Reply> replies;//db에는 없지만 board를 셀렉트할때 들고온다.

    @CreationTimestamp//데이터가 insert될 때 자동으로 현재시간이 들어감
    private LocalDateTime createDate;
}
