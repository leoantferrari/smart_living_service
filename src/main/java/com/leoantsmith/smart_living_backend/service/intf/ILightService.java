package com.leoantsmith.smart_living_backend.service.intf;

import com.leoantsmith.smart_living_backend.controller.dtos.LightDTO;
import com.leoantsmith.smart_living_backend.controller.dtos.LightRequestDTO;

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
