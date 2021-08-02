package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @ColumnDefault("0")//숫자로 사용
    private int count; //조회수

    @ManyToOne//Board가 Many고 User가 one이다.
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트 저장할 수 있다.

    @CreationTimestamp//데이터가 insert될 때 자동으로 현재시간이 들어감
    private Timestamp createDate;


}
