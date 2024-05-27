package com.liquorlogic.userservice.service;

import com.liquorlogic.userservice.entity.User;
import com.liquorlogic.userservice.enums.Role;
import com.liquorlogic.userservice.enums.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    List<User> findAllByType(Role userRole);

    List<User> findAllByStatus(Status status);

    List<User> findByUsername(String username);
    Optional<User> findUserById(UUID id);
    List<User> findByContact(String contact);
    List<User> findByEmail(String email);
    void updateResetToken(UUID userId, int resetToken);
    Optional<User> findUserByResetToken(int resetToken);
}
