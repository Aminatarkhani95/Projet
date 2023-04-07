package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapsRequest {
    private String title ;
    private  Boolean isUrgent;
    private  String image;
    private Date time;
    private String longitude;
    private String latitude;
}
