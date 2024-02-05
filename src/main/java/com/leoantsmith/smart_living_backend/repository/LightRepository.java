package com.leoantsmith.smart_living_backend.repository;

import com.leoantsmith.smart_living_backend.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LightRepository extends JpaRepository<Light, Long> {
    @Query("SELECT e FROM Light e WHERE 6371 * 2 * ASIN(SQRT(POWER(SIN((:givenLat - e.lat) * pi()/180 / 2), 2) + COS(:givenLat * pi()/180) * COS(e.lat * pi()/180) * POWER(SIN((:givenLon - e.lon) * pi()/180 / 2), 2))) <= :distance")
    List<Light> findLightsByLocationAndDistance(@Param("givenLat") double givenLat, @Param("givenLon") double givenLon,
                                                           @Param("distance") double distance);

    @Query("SELECT e FROM Light e WHERE e.isOn = true and e.lastActive <= :threshold")
    List<Light> findLightsThatAreOnAndLastActiveBeforeThreshold(@Param("threshold") Date threshold);

}
