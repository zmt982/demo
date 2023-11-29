package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setFirstname(entity.getFirstname());
        userDto.setLastname(entity.getLastname());
        userDto.setEmail(entity.getEmail());
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User entity = new User();
        entity.setFirstname(userDto.getFirstname());
        entity.setLastname(userDto.getLastname());
        return entity;
    }
    public List<UserDto> toDto(List<User> entities){
        return entities.stream().map(this::toDto).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<User> toEntity(List<UserDto> dtoList){
        return dtoList.stream().map(this::toEntity).filter(Objects::nonNull).collect(Collectors.toList());
    }
}