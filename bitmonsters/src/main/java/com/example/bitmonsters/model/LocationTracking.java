package com.example.bitmonsters.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "location_tracking")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class LocationTracking implements Serializable{

    @Id
    private Long location_tracking_id;

    @NotBlank
    private Long mb_id;

    private Long location_id;

    private LocalDateTime datetime;

    public Long getLocation_tracking_id() {
        return location_tracking_id;
    }

    public void setLocation_tracking_id(Long location_tracking_id) {
        this.location_tracking_id = location_tracking_id;
    }

    public Long getMb_id() {
        return mb_id;
    }

    public void setMb_id(Long mb_id) {
        this.mb_id = mb_id;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}



