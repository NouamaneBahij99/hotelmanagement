package com.MachayBahij.hostelbookingsystem.service;

import com.MachayBahij.hostelbookingsystem.entity.Room;
import com.MachayBahij.hostelbookingsystem.exeptions.UserNotFound;
import com.MachayBahij.hostelbookingsystem.repository.HostelRepository;
import com.MachayBahij.hostelbookingsystem.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;
    private HostelRepository hostelRepository;

    public  void createRoom(Long hostelId, Room room){
        Room room1 = roomRepository.findByName(room.getName());
        if(room1 != null){
            throw new UserNotFound("Room already exists");
        }
        hostelRepository.findById(hostelId).
                map(hostel -> {room.setHostel(hostel);
                return roomRepository.save(room);})
                .orElseThrow(() -> new UserNotFound("Not found room with id " + hostelId));
    }
    public  void  updateRoom(Room room, String name){
        Room room1 = roomRepository.findByName(name);
        if (room1 == null){
            throw new UserNotFound("%s not found" .format(name));
        }
        room1.setName(room.getName());
        room1.setOccupant(room.getOccupant());
        room1.setCost(room.getCost());
        roomRepository.save(room1);
    }
    public  void deleteRoom(String name){
        Room room = roomRepository.findByName(name);
        if (room == null) {
            throw new UserNotFound("%s not found".format(name));
        }
        roomRepository.delete(room);
    }
    public Room findRoom(String name){
        Room room = roomRepository.findByName(name);
        if (room == null){
            throw new UserNotFound("%s not found" .format(name));
        }
        room.setName(room.getName());
        room.setOccupant(room.getOccupant());
        return room;
    }
    public List<Room> findAllRoomByHostelName(String name){
       if (!hostelRepository.existsByName(name)){
           throw new UserNotFound("%s not found".format(name));
       }
       return roomRepository.findByHostelName(name);
    }
}
