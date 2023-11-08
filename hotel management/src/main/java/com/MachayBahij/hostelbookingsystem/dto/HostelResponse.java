package com.MachayBahij.hostelbookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelResponse {
    private String HostelName;
    private String phoneNumber;
}
