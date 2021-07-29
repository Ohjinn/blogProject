package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(DATA)
@RestController
public class HttpControllerTest {

    //인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
    //http://localhost:8080/http/get(select)
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청: " + m.getId() +", "+m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    //http://localhost:8080/http/post(insert)
    @PostMapping("/http/post")//스프링부트의 MessageConverter가 하는 일.
    public String postTest(@RequestBody Member m){
        return "post 요청:" + m.getId() +", "+m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    //http://localhost:8080/http/put(update)
    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    //http://localhost:8080/http/delete(delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }


}
