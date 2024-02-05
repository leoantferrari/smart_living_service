package com.leoantsmith.smart_living_backend.controller;


import com.leoantsmith.smart_living_backend.controller.dtos.LightRequestDTO;
import com.leoantsmith.smart_living_backend.service.intf.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/light")
public class LightController {

    @Autowired
    private ILightService lightService;

    @PutMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody LightRequestDTO lightRequestDTO) {
        lightService.handleLightRequest(lightRequestDTO);
        return ResponseEntity.ok().build();
    }
}
