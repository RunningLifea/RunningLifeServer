package com.example.RunningLifeServer.service;

import com.example.RunningLifeServer.domain.User;
import com.example.RunningLifeServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void signUp(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);

    }

    private void validateDuplicateMember(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                } );
    }

    public Optional<User> findByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        return user;
    }

    public void update(User user, String name) {
        if (!Objects.equals(name, user.getName())) {
            validateDuplicateMember(user);
        }
        userRepository.updateUser(user.getName(), user.getDistance(), user.getTime(), name);
    }

}
