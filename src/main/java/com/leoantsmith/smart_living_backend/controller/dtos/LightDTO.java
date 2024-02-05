package com.leoantsmith.smart_living_backend.controller.dtos;

import java.util.Date;

public class LightDTO {

    private long id;

    private Double lon;

    private Double lat;

    private Boolean isOn;

    private Date lastActive;

    private String turnOnTrigger;

    private String turnOffTrigger;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public String getTurnOnTrigger() {
        return turnOnTrigger;
    }

    public void setTurnOnTrigger(String turnOnTrigger) {
        this.turnOnTrigger = turnOnTrigger;
    }

    public String getTurnOffTrigger() {
        return turnOffTrigger;
    }

    public void setTurnOffTrigger(String turnOffTrigger) {
        this.turnOffTrigger = turnOffTrigger;
    }
}
