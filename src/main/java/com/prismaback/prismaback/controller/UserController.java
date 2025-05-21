package com.prismaback.prismaback.controller;

import com.prismaback.prismaback.DTO.UserDTO;
import com.prismaback.prismaback.exception.user.UserAlreadyExistsException;
import com.prismaback.prismaback.service.UserService;

import lombok.RequiredArgsConstructor;

import com.prismaback.prismaback.model.User;
import com.prismaback.prismaback.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Email ja registrat");
        }
        User user = UserService.toEntity(userDto);
        User newUser = userRepository.save(user);
        UserDTO created = UserService.toDto(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
