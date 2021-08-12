package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Data//getter, setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity//클래스를 테이블화 하기 위한 annotation, User 클래스가 MySQL에 테이블로 생성된다.
//@DynamicInsert insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
    private int id; //시퀀스, auto_increment하는 전략을 가져갈 것이다.

    @Column(nullable = false, length = 30, unique = true)
    private String username; //아이디

    @Column(nullable = false, length = 100) //=>해쉬로 변경해서 비밀번호 암호화
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'") 이렇게 하면 default값이 안들어간다.
    //DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다. // admin, user, manager

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;
}
