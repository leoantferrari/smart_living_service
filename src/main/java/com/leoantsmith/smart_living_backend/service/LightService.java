package com.leoantsmith.smart_living_backend.service;

import com.leoantsmith.smart_living_backend.controller.dtos.LightDTO;
import com.leoantsmith.smart_living_backend.controller.dtos.LightRequestDTO;
import com.leoantsmith.smart_living_backend.model.Light;
import com.leoantsmith.smart_living_backend.repository.LightRepository;
import com.leoantsmith.smart_living_backend.service.intf.ILightHandlerService;
import com.leoantsmith.smart_living_backend.service.intf.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LightService implements ILightService {

    private final Logger logger = Logger.getLogger(LightService.class.getName());

    @Autowired
    LightRepository lightRepository;

    @Autowired
    ILightHandlerService lightHandlerService;

    @Override
    public void handleLightRequest(LightRequestDTO lightRequestDTO) {
        // Retrieve Lights within Proximity
        List<Light> lightList = lightRepository.findLightsByLocationAndDistance(lightRequestDTO.getCoordinates().getLat(), lightRequestDTO.getCoordinates().getLon(), lightRequestDTO.getDistance());
        // Check if on, and change active time
        for (Light light: lightList) {
            logger.log(Level.INFO,"Activating light with id {0}", light.getId());
            if (!light.isOn()) {
                lightHandlerService.turnOnLight(light.getTurnOnTrigger());
            }
            light.setOn(true);
            light.setLastActive(new Date());
            lightRepository.save(light);
        }
    }

    @Override
    public LightDTO createLight(LightDTO light) {
        Light toSave = new Light();

        toSave.setTurnOffTrigger(light.getTurnOffTrigger());
        toSave.setTurnOnTrigger(light.getTurnOnTrigger());
        toSave.setOn(light.getOn());
        toSave.setLon(light.getLon());
        toSave.setLat(light.getLat());
        toSave.setLastActive(new Date());

        toSave = lightRepository.save(toSave);

        light.setId(toSave.getId());
        light.setLastActive(new Date());

        return light;
    }

    @Override
    public void deactivateLights() {
        List<Light> lightList = lightRepository.findAll();
        // Check if on, and change active time
        for (Light light: lightList) {
            logger.log(Level.INFO,"Deactivating light with id {0}", light.getId());
            if (light.isOn()) {
                lightHandlerService.turnOffLight(light.getTurnOffTrigger());
            }
            light.setOn(false);
            light.setLastActive(new Date());
            lightRepository.save(light);
        }
    }
}
