package com.indistudia.botbackend.controller;

import com.indistudia.botbackend.controller.dto.UserDto;
import com.indistudia.botbackend.controller.dto.CreateUserDto;
import com.indistudia.botbackend.mappers.UserMapper;
import com.indistudia.botbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @PostMapping
    public UserDto create(@RequestBody CreateUserDto dto) {
        return userMapper.toDto(userService.create(dto));
    }

    @PostMapping("/get-or-create")
    public UserDto getOrCreate(@RequestBody CreateUserDto dto) {
        return userMapper.toDto(userService.getOrCreateByTelegramId(dto));
    }
}
