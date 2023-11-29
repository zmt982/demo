package com.example.demo.controller;

import com.example.demo.model.PageResponse;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/pageable")
    public ResponseEntity<PageResponse<UserDto>> getAllPageable(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String firstname
    ) {
        return ResponseEntity.ok(userService.getAllPageable(pageNumber, pageSize, firstname));
    }
}
