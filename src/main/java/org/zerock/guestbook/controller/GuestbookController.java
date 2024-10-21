package org.zerock.guestbook.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2
public class GuestbookController {

    // GetMapping 어노테이션 파라미터에 String 배열을 넣어 여러 endpoint를 mapping할 수 있다.
    @GetMapping({"/", "/list"})
    public String list() {

        log.info("GET list");

        // Thymeleaf 라이브러리 사용
        // resources/templates 하위 디렉토리에서 사용할 page가 있는 경로를 직접 작성해준다.
        return "/guestbook/list";
    }
}
