package com.example.security.service;

import com.example.security.dto.MapsRequest;
import com.example.security.dto.MapsResponse;
import com.example.security.entities.Maps;
import com.example.security.repositories.MapsRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class MapsService {
    private  final MapsRepo mapsRepo;

  public void create(MapsRequest mapsRequest){
      Maps maps = Maps.builder()
              .title(mapsRequest.getTitle())
              .image(mapsRequest.getImage())
              .time(mapsRequest.getTime())
              .longitude(mapsRequest.getLongitude())
              .latitude(mapsRequest.getLatitude())
              .build();
      mapsRepo.save(maps);
      log.info("Maps{} saved",+maps.getId());

  }

    public List<MapsResponse> getALLMaps() {
        List<Maps>  maps = mapsRepo.findAll();
        return maps.stream().map(this::mapToMapsResponse).toList();

    }

    private MapsResponse mapToMapsResponse(Maps maps) {
      return MapsResponse.builder()
              .id(maps.getId())
              .title(maps.getTitle())
              .image(maps.getImage())
              .time(maps.getTime())
              .isUrgent(maps.getIsUrgent())
              .longitude(maps.getLongitude())
              .latitude(maps.getLatitude())
              .build();
    }
}
