package com.indistudia.botbackend.service;

import com.indistudia.botbackend.domain.User;
import com.indistudia.botbackend.controller.dto.CreateUserDto;
import com.indistudia.botbackend.mappers.UserMapper;
import com.indistudia.botbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private Optional<User> findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            log.atError().addKeyValue("userId", userId).log("User with this id not found");
            return new RuntimeException("User with id " + userId + " not found");
        });
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User create(CreateUserDto dto) {
        return save(userMapper.toUser(dto));
    }

    @Transactional
    public User getOrCreateByTelegramId(CreateUserDto dto) {
        var userFromDb = findByTelegramId(dto.telegramId());

        if (userFromDb.isPresent()) {
            return userFromDb.get();
        }
        var user = userMapper.toUser(dto);

        return save(user);
    }
}
