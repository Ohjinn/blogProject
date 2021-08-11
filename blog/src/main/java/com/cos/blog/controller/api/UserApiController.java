package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired//DI
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){//username, password, email 세 개만 받는다.
        System.out.println("UserApiController : save 호출됨");
        //실제로 DB에 insert를 하고 아래에서 return 하면 된다.
        user.setRole(RoleType.USER);
        int result = userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), result);//자바 오브젝트를 JSON 으로 변환해서 리턴 (Jackson)
    }
}
