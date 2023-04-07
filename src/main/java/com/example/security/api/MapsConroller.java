package com.example.security.api;

import com.example.security.dto.MapsRequest;
import com.example.security.dto.MapsResponse;
import com.example.security.service.MapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")


public class MapsConroller  {
    private final  MapsService mapsService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@RequestBody MapsRequest mapsRequest){
        mapsService.create(mapsRequest);

    }
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<MapsResponse> getAllMaps(){
       return mapsService.getALLMaps();

}





}
