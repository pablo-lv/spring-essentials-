package com.plucas.spring.essentials.repositories;

import com.plucas.spring.essentials.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {
}
