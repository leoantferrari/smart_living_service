package com.leoantsmith.SmartLivingBackend.service;

import com.leoantsmith.SmartLivingBackend.controller.dtos.LightDTO;
import com.leoantsmith.SmartLivingBackend.controller.dtos.LightRequestDTO;
import com.leoantsmith.SmartLivingBackend.model.Light;
import com.leoantsmith.SmartLivingBackend.repository.LightRepository;
import com.leoantsmith.SmartLivingBackend.service.intf.ILightHandlerService;
import com.leoantsmith.SmartLivingBackend.service.intf.ILightService;
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
        System.out.print(lightRequestDTO);
        // Retrieve Lights within Proximity
        List<Light> lightList = lightRepository.findLightsByLocationAndDistance(lightRequestDTO.getCoordinates().getLat(), lightRequestDTO.getCoordinates().getLon(), lightRequestDTO.getDistance());
        // Check if on, and change active time
        for (Light light: lightList) {
            if (!light.getOn()) {
                lightHandlerService.turnOnLight(light.getTurnOnTrigger());
            }
            light.setOn(true);
            light.setLastActive(new Date());
            logger.log(Level.INFO, "Activating light " + light.getId() +" lol" + light.getTurnOnTrigger());
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
            if (light.getOn()) {
                lightHandlerService.turnOffLight(light.getTurnOffTrigger());
            }
            light.setOn(false);
            light.setLastActive(new Date());
            logger.log(Level.INFO,"Deactivating light " + light.getId() +" lol" + light.getTurnOffTrigger());
            lightRepository.save(light);
        }
    }
}
