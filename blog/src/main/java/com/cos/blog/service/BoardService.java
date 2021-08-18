package com.cos.blog.service;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다");
                });
    }

    @Transactional
    public void 글삭제하기(int id, PrincipalDetail principal){
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글이 존재하지 않습니다");
                });

        if(board.getUser().getId() != principal.getUser().getId()){
            throw new IllegalArgumentException("해당 글을 삭제할 권한이 없습니다.");
        }
         boardRepository.deleteById(id);
    }
}
