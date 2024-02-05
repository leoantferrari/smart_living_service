package com.leoantsmith.smart_living_backend.controller.dtos;

public class LightRequestDTO {
    double distance;

    CoordinatesDTO coordinates;

    public double getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public CoordinatesDTO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDTO coordinates) {
        this.coordinates = coordinates;
    }
}
