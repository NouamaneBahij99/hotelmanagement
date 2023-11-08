package com.MachayBahij.hostelbookingsystem.service;

import com.MachayBahij.hostelbookingsystem.dto.HostelResponse;
import com.MachayBahij.hostelbookingsystem.entity.Hostel;
import com.MachayBahij.hostelbookingsystem.entity.User;
import com.MachayBahij.hostelbookingsystem.exeptions.UserNotFound;
import com.MachayBahij.hostelbookingsystem.repository.HostelRepository;
import com.MachayBahij.hostelbookingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostelService {

    private HostelRepository hostelRepository;
    private UserRepository userRepository;

    public void createHostel(String phoneNumber, Hostel hostel){
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if(!user.isPresent()){
            throw new UserNotFound("User not found");
        }
        Hostel hostel1 = hostelRepository.findByName(hostel.getName());
        if(hostel1 != null){
            throw new UserNotFound("Hostel already exists");
        }
        hostel.setUser(user.get());
        hostelRepository.save(hostel);

    }
    public  void  updateHostel(Hostel hostel, String name){
        Hostel hostel1 = hostelRepository.findByName(name);
        if (hostel1 == null){
            throw new UserNotFound("%s not found" .format(name));
        }
        hostel1.setName(hostel.getName());
        hostel1.setPhoneNumber(hostel.getPhoneNumber());
        hostelRepository.save(hostel1);
    }
    public HostelResponse findHostel(String name){
        Hostel hostel = hostelRepository.findByName(name);
        HostelResponse hostelResponse = new HostelResponse();
        if (hostel == null){
            throw new UserNotFound("%s not found" .format(name));
        }
        hostelResponse.setHostelName(hostel.getName());
        hostelResponse.setPhoneNumber(hostel.getPhoneNumber());
        return hostelResponse;
    }
    public List<Hostel> findAllHostelByUserPhoneNumber(String phoneNumber){
        if (!userRepository.existsByPhoneNumber(phoneNumber)){
            throw  new UserNotFound("%s not found".format(phoneNumber));
        }
        return hostelRepository.findByUserPhoneNumber(phoneNumber);
    }
    public void deleteHostel(String name){
        Hostel hostel = hostelRepository.findByName(name);
        if (hostel == null){
            throw new UserNotFound("%s not found" .format(name));
        }
        hostelRepository.delete(hostel);
    }
}
