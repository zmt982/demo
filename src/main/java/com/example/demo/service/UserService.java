package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User entity= user.orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = userMapper.toDto(entity);
        return userDto;
    }
}
