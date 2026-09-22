package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.request.CreateUserRequest;
import com.ai.ailanguageteacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/create")
    public void create(@RequestBody @Valid CreateUserRequest request) {
        service.create(request);
    }
}
