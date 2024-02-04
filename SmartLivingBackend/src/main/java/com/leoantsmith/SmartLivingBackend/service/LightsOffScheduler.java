package com.leoantsmith.SmartLivingBackend.service;

import com.leoantsmith.SmartLivingBackend.model.Light;
import com.leoantsmith.SmartLivingBackend.repository.LightRepository;
import com.leoantsmith.SmartLivingBackend.service.intf.ILightHandlerService;
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
    private final Logger logger = Logger.getLogger(LightService.class.getName());

    @Autowired
    ILightHandlerService lightHandlerService;

    @Autowired
    LightRepository lightRepository;

    /**
     * Every minute turn off the lights that have not been active in a minute
     */
    @Scheduled(fixedRate = 10000)
    public void performTask() {
        logger.log(Level.INFO,"Checking for lights to deactivate at  "+LocalDateTime.now());
        Date threshold = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1));
        List<Light> lightList = lightRepository.findLightsThatAreOnAndLastActiveBeforeThreshold(threshold);
        for (Light light: lightList) {
            lightHandlerService.turnOffLight(light.getTurnOffTrigger());
            light.setOn(false);
            light.setLastActive(new Date());
            logger.log(Level.INFO,"Deactivating light " + light.getId() +" lol" + light.getTurnOffTrigger());
            lightRepository.save(light);
        }
    }
}
