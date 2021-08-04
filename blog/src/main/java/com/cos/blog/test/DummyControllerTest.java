package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//페이지 이동이 아닌 응답만을 위한
public class DummyControllerTest {

    @Autowired//의존성주입 DI
    private UserRepository userRepository;
    //스프링이 컴포넌트 스캔을 할 때 UserRepository interface에서 UserRepository를 메모리에 띄워주기 때문에 Autowired를 통해

    //http://localhost:8000/blog/dummy/join( 요청 )
    //http의 body에 username, password, email 데이터를 가지고(요청)
    @PostMapping("/dummy/join")//회원가입 Insert를 위한 postMapping
    public String join(User user){ //key = value형의 데이터를 받는다.(약속된 규칙)
        System.out.println("ID = " + user.getId());
        System.out.println("username = " + user.getUserName());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        System.out.println("role = " + user.getRole());
        System.out.println("createDate = " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
