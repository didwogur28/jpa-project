package com.yangjaehyuk.springboot.web;

import com.yangjaehyuk.springboot.config.auth.dto.SessionUser;
import com.yangjaehyuk.springboot.service.posts.PostsService;
import com.yangjaehyuk.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        /*
             (SessionUser) httpSession.getAttribute("user")
              - 앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
              - 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음

             if (user != null)
              - 세션에 저장된 값이 있을 때만 model에 userName으로 등록
              - 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됨
        */
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable String id, Model model) {

        Long ids;
        ids = Long.valueOf(id);

        PostsResponseDto dto = postsService.findById(ids);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}

