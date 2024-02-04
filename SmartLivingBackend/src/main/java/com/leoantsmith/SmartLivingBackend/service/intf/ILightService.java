package com.leoantsmith.SmartLivingBackend.service.intf;

import com.leoantsmith.SmartLivingBackend.controller.dtos.LightDTO;
import com.leoantsmith.SmartLivingBackend.controller.dtos.LightRequestDTO;

public interface ILightService {

    /**
     * Handle a light request, using coordinates and the distance
     *
     * @param lightRequestDTO request info
     */
    void handleLightRequest(LightRequestDTO lightRequestDTO);

    /**
     * Create a light
     *
     * @param light the light to create
     * @return the created light
     */
    LightDTO createLight(LightDTO light);

    /**
     * Turn off all lights in the system
     */
    void deactivateLights();
}
