package com.MachayBahij.hostelbookingsystem;

import com.MachayBahij.hostelbookingsystem.entity.User;
import com.MachayBahij.hostelbookingsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HostelBookingSystemApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void TestCreateUser(){
		User user = new User(1L,"0568975326","Aymane","Aymaneetc@gmail.com","123456", null,null);
		userRepository.save(user);
	}

}
