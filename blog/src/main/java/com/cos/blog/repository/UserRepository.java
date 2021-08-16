package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//DAO jsp에서 data access object 라고 생각하면 된다.
//자동으로 bean 등록이 된다.
//@Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

}

//    JPA Naming 쿼리 전략
//    Select * From user Where userName = ?1 and password = ?2;
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user Where userName = ?1 and password = ?2", nativeQuery = true)
//    User login(String userName, String password);            두가지 모두 사용 가능.
//로그인을 위한 함수