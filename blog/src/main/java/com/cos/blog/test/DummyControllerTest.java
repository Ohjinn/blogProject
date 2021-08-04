package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RestController//페이지 이동이 아닌 응답만을 위한
public class DummyControllerTest {

    @Autowired//의존성주입 DI
    private UserRepository userRepository;
    //스프링이 컴포넌트 스캔을 할 때 UserRepository interface에서 UserRepository를 메모리에 띄워주기 때문에 Autowired를 통해

    //save는 id를 전달하지 않으면 insert
    //id를 전달하면 id에 대한 데이터가 있으면 update
    //id전달에 id에 대한 데이터가 없으면 insert
    //email, password


    @Transactional//함수 종료시에 자동 commit 하면서 변경됐으면 flush(update)를 수행, 변경감지, 수정 -> 더티체킹
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){//json데이터 받기 위한 RequestBody, 스프링이 java object로 변환해서 전송
        System.out.println("id = " + id);
        System.out.println("password = " + requestUser.getPassword());
        System.out.println("email = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{//함수를 넘기기 위해 람다가 생겼다
           return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        requestUser.setId(id);
//        requestUser.setUserName("ssar");
//        userRepository.save(user);
        //save는 insert할 때 쓰는건데 save할때 id값을 넣어주면 update를 해 준다. 없는 값은 null처리를 해 주기 때문에 save는 잘 쓰지 않는다.

        //Transactional의 더티체킹
        return user;
    }

    //http://localhost8000/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //한 페이지당 두 건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    //{id} 주소로 파라미터 전달받을 수 있음.
    //http://localhost8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4를 찾을때 내가 데이터베이스에서 못찾으면 user가 null이 되기 때문에 null이 return 된다.
        // 따라서 Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해라.
//1
//          User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//                //빈 객체를 return 하는건 null은 아니다.
//            }
//        });
//2        findById(id).get() - 상관말고 null 도 가져와

//3 기본적으로 Exception으로 처리해라.
//          User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
//            }
//        });
//        return user;

//4 람다식으로 new Supplier부분 전부 생략할 수 있다.
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
        //@RestController - html파일이 아니라 data를 리턴해주는 컨트롤러.
        // 요청 : 웹 브라우저
        // user 객체 = 자바 오브젝트
        // 변환 (웹 브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
        // 스프링부트 = MessageConverter 라는 애가 응답시에 자동 작동해서
        // 만약 자바 오브젝트를 return 하게되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 Json으로 변환해서 return 해준다.
        return user;
    }



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
