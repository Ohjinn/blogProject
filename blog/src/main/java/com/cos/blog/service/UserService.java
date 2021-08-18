package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service//spring이 컴포넌트 스캔을 통해 bean에 등록. IoC를 해준다.
public class UserService {

    @Autowired//DI
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional//여러개의 트랜젝션이 모여서 하나의 서비스가 될 수 있기 때문에, 또한 대부분 그렇기 때문에
    public void 회원가입(User user){
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword); //해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
//    @Transactional(readOnly = true)//Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//    public User 로그인(User user){
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }

    @Transactional
    public void 회원수정(User user){
        // 수정시에는 JPA 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정하는게 최선
        // select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다
        User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
           return new IllegalArgumentException("회원 찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistence.setPassword(encPassword);
        persistence.setEmail(user.getEmail());
        //회원 수정 함수 종료시 = 서비스 종료시 = 트랜잭션 종료 = commit이 자동으로 된다.
        //영속화된 persistance 객체의 변화가 감지되면 더티체킹이 돼서 변화된 것들을 update문을 날려준다.
    }
}
