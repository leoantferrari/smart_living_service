package com.leoantsmith.SmartLivingBackend.service.intf;

public interface ILightHandlerService {

    /**
     * Turn off light by triggering URL
     * @param url the url to trigger
     */
    void turnOffLight(String url);

    /**
     * Turn on light by triggering URL
     *
     * @param url the url to trigger
     */
    void turnOnLight(String url);
}
