package com.leoantsmith.SmartLivingBackend.service;

import com.leoantsmith.SmartLivingBackend.controller.dtos.LightRequestDTO;
import com.leoantsmith.SmartLivingBackend.model.Light;
import com.leoantsmith.SmartLivingBackend.repository.LightRepository;
import com.leoantsmith.SmartLivingBackend.service.intf.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;

@Service
public class LightService implements ILightService {

    @Autowired
    LightRepository lightRepository;

    @Override
    public void handleLightRequest(LightRequestDTO lightRequestDTO) {
        // Retrieve Lights within Proximity
        List<Light> lightList = lightRepository.findLightsByLocationAndDistance(lightRequestDTO.getCoordinates().getLat(), lightRequestDTO.getCoordinates().getLon(), lightRequestDTO.getDistance());
        // Check if on, and change active time
        System.out.print(lightList.size());
        // If not on, turn it on
    }
}
