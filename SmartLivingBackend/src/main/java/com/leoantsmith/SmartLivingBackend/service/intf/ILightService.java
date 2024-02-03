package com.leoantsmith.SmartLivingBackend.service.intf;

import com.leoantsmith.SmartLivingBackend.controller.dtos.LightRequestDTO;

public interface ILightService {
    void handleLightRequest(LightRequestDTO lightRequestDTO);
}
