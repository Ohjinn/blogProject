package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//컨트롤러라는 메서드가 붙으면 파일을 리턴함.
@Controller
public class TempControllerTest {

    //http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome(){
        //파일 리턴 기본경로 : src/main/resources/static
        //리턴명 : /home.html
        //풀경로 : src/main/resources/static/home.html
        return "/home.html";
    }

}
