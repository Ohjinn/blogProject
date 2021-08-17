package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //컨트롤러에서 세션을 어떻게 찾는지?
    //@AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"", "/"})
    public String index(Model model){
        model.addAttribute("boards", boardService.글목록());
        return "index";//restController가 아니기 때문에 viewResolver 작동한다. 따라서 모델의 정보가 이동한다.
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
