package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(DATA)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest: ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        //lombok의 builder패턴은 생성자의 순서를 지키지 않아도 된다는 장점이 있기 때문에 실수 할 가능성이 낮아진다.
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG + "getter" + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG + "getter" + m.getUsername());
        return "lombok test 완료:";
    }

    //인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
    //http://localhost:8080/http/get(select)
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청: " + m.getId() +", "+m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    //http://localhost:8080/http/post(insert)
    @PostMapping("/http/post")//스프링부트의 MessageConverter가 하는 일.
    public String postTest(@RequestBody Member m){//body의 data를 받기 위한 object로 mapping하기 위한 annotation
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
