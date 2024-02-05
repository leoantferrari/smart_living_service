package com.leoantsmith.smart_living_backend.service;

import com.leoantsmith.smart_living_backend.model.Light;
import com.leoantsmith.smart_living_backend.repository.LightRepository;
import com.leoantsmith.smart_living_backend.service.intf.ILightHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LightsOffScheduler {
    private final Logger logger = Logger.getLogger(LightsOffScheduler.class.getName());

    @Autowired
    ILightHandlerService lightHandlerService;

    @Autowired
    LightRepository lightRepository;

    /**
     * Every minute turn off the lights that have not been active in a minute
     */
    @Scheduled(fixedRate = 10000)
    public void performTask() {
        logger.log(Level.INFO,"Checking for lights to deactivate at {0}", LocalDateTime.now());
        Date threshold = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1));
        List<Light> lightList = lightRepository.findLightsThatAreOnAndLastActiveBeforeThreshold(threshold);
        List<Light> alllights = lightRepository.findAll();
        for (Light light: lightList) {
            logger.log(Level.INFO,"Deactivating light with id {0}", light.getId());
            lightHandlerService.turnOffLight(light.getTurnOffTrigger());
            light.setOn(false);
            light.setLastActive(new Date());
            lightRepository.save(light);
        }
        for (Light light:alllights) {
            logger.log(Level.INFO, "LIGHT"+light.getLon()+" "+light.getLat());
        }
     }
}
