package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.request.CreateUserRequest;
import com.ai.ailanguageteacher.exception.ApiValidationException;
import com.ai.ailanguageteacher.exception.CreateEntityException;
import com.ai.ailanguageteacher.exception.NotFoundException;
import com.ai.ailanguageteacher.model.ChatUser;
import com.ai.ailanguageteacher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void create(CreateUserRequest request) {
        repository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new ApiValidationException("User with this email already exists");
                });

        var user = new ChatUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        try {
            var saved = repository.save(user);
            log.debug("User with email: {} is successfully saved with id: {}", request.getEmail(), saved.getId());
        } catch (Exception e) {
            log.error("Failed to save user with email: {}", request.getEmail());
            throw new CreateEntityException("User is not created with email: %s".formatted(request.getEmail()));
        }
    }

    protected ChatUser findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: %d is not present".formatted(id)));
    }
}
