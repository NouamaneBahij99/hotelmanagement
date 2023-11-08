package com.MachayBahij.hostelbookingsystem.service;

import com.MachayBahij.hostelbookingsystem.entity.MyUserDetails;
import com.MachayBahij.hostelbookingsystem.entity.User;
import com.MachayBahij.hostelbookingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (!user.isPresent()) {
            throw  new UsernameNotFoundException("Not found" + phoneNumber);
        }
        return user.map(MyUserDetails::new).get();
    }
}
