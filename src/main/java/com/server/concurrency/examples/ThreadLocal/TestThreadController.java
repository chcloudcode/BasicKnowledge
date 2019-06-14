package com.server.concurrency.examples.ThreadLocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/threadLocal")
public class TestThreadController {

    @GetMapping("/test")
    public Long get(){
        return RequestHolder.getId();
    }

}
