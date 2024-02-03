package com.leoantsmith.SmartLivingBackend.controller;


import com.leoantsmith.SmartLivingBackend.controller.dtos.LightRequestDTO;
import com.leoantsmith.SmartLivingBackend.service.intf.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/light")
public class LightController {

    @Autowired
    private ILightService lightService;

    @GetMapping("/activate")
    public String activate() {
        lightService.handleLightRequest(new LightRequestDTO());
        return "Hello mate, the time now is " + new Date() + "\n";
    }
}
