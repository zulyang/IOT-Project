package com.example.bitmonsters.repository;

import com.example.bitmonsters.model.LocationTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTrackingRepository extends JpaRepository<LocationTracking, Long> {

}



