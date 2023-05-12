package com.plucas.spring.essentials.repositories;

import com.plucas.spring.essentials.entities.UserAccount;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}