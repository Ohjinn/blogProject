package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service//spring이 컴포넌트 스캔을 통해 bean에 등록. IoC를 해준다.
public class BoardService {

    @Autowired//DI
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user){//title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public List<Board> 글목록(){
        return boardRepository.findAll();
    }
}
