package com.indistudia.mediatrackerbotspring.service;

import com.indistudia.mediatrackerbotspring.domain.User;
import com.indistudia.mediatrackerbotspring.repository.UserRepository;
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

    public Optional<User> findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            log.atError().addKeyValue("userId", userId).log("User with this id not found");
            return new RuntimeException("User with id " + userId + " not found");
        });
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User getOrCreateByTelegramId(String username, Long telegramId) {
        var userFromDb = findByTelegramId(telegramId);

        if (userFromDb.isPresent()) {
            return userFromDb.get();
        }
        var user = User.builder()
                .telegramId(telegramId)
                .username(username)
                .build();

        save(user);

        return user;
    }
}
