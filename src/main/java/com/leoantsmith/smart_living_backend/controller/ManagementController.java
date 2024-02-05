package com.leoantsmith.smart_living_backend.controller;

import com.leoantsmith.smart_living_backend.controller.dtos.LightDTO;
import com.leoantsmith.smart_living_backend.service.intf.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ManagementController {

    @Autowired
    private ILightService lightService;

    @PostMapping("/create")
    public ResponseEntity<LightDTO> createLight(@RequestBody LightDTO lightDTO) {

        return ResponseEntity.ok(lightService.createLight(lightDTO));
    }

    @PostMapping("/deactivateAll")
    public ResponseEntity<String> deactivateLights() {

        lightService.deactivateLights();
        return ResponseEntity.ok("Deactivated lights");
    }
}
