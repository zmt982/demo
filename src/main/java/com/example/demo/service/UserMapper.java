package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setFirstname(entity.getFirstname());
        userDto.setLastname(entity.getLastname());
        return userDto;
    }
}
