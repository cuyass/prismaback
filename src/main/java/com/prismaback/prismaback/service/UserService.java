package com.prismaback.prismaback.service;

import com.prismaback.prismaback.DTO.UserDTO;
import com.prismaback.prismaback.model.User;

public class UserService {
    public static UserDTO toDto(User user) {
        return UserDTO.builder()
            .id(user.getId())
            .email(user.getEmail())
            .build();
    }
    public static User toEntity(UserDTO dto) {
        return User.builder()
            .id(dto.getId())
            .email(dto.getEmail())
            .build();
    }
}
