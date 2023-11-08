package com.MachayBahij.hostelbookingsystem.service;

import com.MachayBahij.hostelbookingsystem.entity.Booking;
import com.MachayBahij.hostelbookingsystem.entity.Room;
import com.MachayBahij.hostelbookingsystem.exeptions.UserNotFound;
import com.MachayBahij.hostelbookingsystem.repository.BookingRepository;
import com.MachayBahij.hostelbookingsystem.repository.RoomRepository;
import com.MachayBahij.hostelbookingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    public void bookingRoom(String roomName, String userPhoneNumber, Booking booking){
        Room rooms = roomRepository.findByName(roomName);
        if(rooms == null){
            throw new UserNotFound("Room not found");
        }
        if(bookingRepository.countBookByRoomId(rooms.getId()) >= rooms.getOccupant()){
            throw new UserNotFound("Room is full");
        }
        userRepository.findByPhoneNumber(userPhoneNumber) .map(user -> {
            booking.setUser(user);
            booking.setRoom(rooms);
            booking.setStartDate(LocalDateTime.now());
            booking.setEndDate(booking.getEndDate());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new UserNotFound("User not found"));
    }

}
