package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.PageResponse;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    public UserDto getById(Long id) {
        Optional<User> userToFind = userRepository.findById(id);
        User entity = userToFind.orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = userMapper.toDto(entity);
        return userDto;
    }

    public UserDto add(UserDto addDto) {
        User userToAdd = userMapper.toEntity(addDto);
        userToAdd = userRepository.save(userToAdd);
        return userMapper.toDto(userToAdd);
    }

    public UserDto updateById(Long id, UserDto updateDto) {
        Optional<User> userToFind = userRepository.findById(id);
        User userToUpdate = userToFind.orElseThrow(() -> new RuntimeException("No user founded"));
        userToUpdate.setFirstname(updateDto.getFirstname());
        userToUpdate.setLastname(updateDto.getLastname());
        return userMapper.toDto(userToUpdate);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public PageResponse<UserDto> getAllPageable(int pageNumber, int pageSize, String firstname) {
        Page<User> userPage = userRepository.findAllPageable(PageRequest.of(pageNumber, pageSize), firstname);
        PageResponse<User> userPageResponse = new PageResponse<>(userPage);
        return userPageResponse.map(userMapper::toDto);
    }
}