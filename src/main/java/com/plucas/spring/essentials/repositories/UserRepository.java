package com.plucas.spring.essentials.repositories;

import com.plucas.spring.essentials.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);

}
