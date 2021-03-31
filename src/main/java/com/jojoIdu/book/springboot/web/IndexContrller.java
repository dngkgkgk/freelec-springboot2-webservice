package com.jojoIdu.book.springboot.web;

import com.jojoIdu.book.springboot.config.auth.LoginUser;
import com.jojoIdu.book.springboot.config.auth.dto.SessionUser;
import com.jojoIdu.book.springboot.service.posts.PostsService;
import com.jojoIdu.book.springboot.web.domain.posts.Posts;
import com.jojoIdu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoIdu.book.springboot.web.dto.PostsResponseDto;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexContrller {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts",postsService.findAllDesc());
        if(user != null){
            model.addAttribute("name",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        System.out.println("오긴옵니까");
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts",dto);
        return "posts-update";
    }
}
