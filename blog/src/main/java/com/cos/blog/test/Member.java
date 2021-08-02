package com.cos.blog.test;

import lombok.*;

//@Getter @Setter
@Data
@NoArgsConstructor//빈 생성자
//@RequiredArgsConstructor //final에 생성자 넣어주는 constructor
public class Member {

    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private int id;
    private String username;
    private String password;
    private String email;

}
