package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service//spring이 컴포넌트 스캔을 통해 bean에 등록. IoC를 해준다.
public class UserService {

    @Autowired//DI
    private UserRepository userRepository;

    @Transactional//여러개의 트랜젝션이 모여서 하나의 서비스가 될 수 있기 때문에, 또한 대부분 그렇기 때문에
    public int 회원가입(User user){
        try{
            userRepository.save(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("UserService : 회원가입() : " + e.getMessage());
        }
        return -1;
    }
}
