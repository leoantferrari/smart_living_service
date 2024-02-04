package com.leoantsmith.SmartLivingBackend.controller;


import com.leoantsmith.SmartLivingBackend.controller.dtos.LightDTO;
import com.leoantsmith.SmartLivingBackend.controller.dtos.LightRequestDTO;
import com.leoantsmith.SmartLivingBackend.service.intf.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/light")
public class LightController {

    @Autowired
    private ILightService lightService;

    @GetMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody LightRequestDTO lightRequestDTO) {
        lightService.handleLightRequest(lightRequestDTO);
        return ResponseEntity.ok().build();
    }
}
