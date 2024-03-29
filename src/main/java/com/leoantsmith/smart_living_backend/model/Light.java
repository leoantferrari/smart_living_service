package com.leoantsmith.smart_living_backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "light")
public class Light {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "isOn")
    private Boolean isOn;

    @Column(name = "lastActive")
    private Date lastActive;

    @Column(name = "turnOnTrigger")
    private String turnOnTrigger;

    @Column(name = "turnOffTrigger")
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

    public Boolean isOn() {
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
