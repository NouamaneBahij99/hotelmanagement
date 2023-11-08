package com.MachayBahij.hostelbookingsystem.repository;

import com.MachayBahij.hostelbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhoneNumber(String phoneNo);
    boolean existsByPhoneNumber(String phoneNumber);
}
