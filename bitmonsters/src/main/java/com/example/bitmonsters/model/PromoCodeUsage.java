package com.example.bitmonsters.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "promo_code_usage")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class PromoCodeUsage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promo_code_usage_id;


    private LocalDateTime datetime;

    @NotNull
    private String promo_code_id;

    @NotNull
    private Long retailer_id;

    @NotNull
    private int count;


    public Long getPromo_code_usage_id() {
        return promo_code_usage_id;
    }

    public void setPromo_code_usage_id(Long promo_code_usage_id) {
        this.promo_code_usage_id = promo_code_usage_id;
    }

    public Long getRetailer_id() {
        return retailer_id;
    }

    public void setRetailer_id(Long retailer_id) {
        this.retailer_id = retailer_id;
    }

    public String getPromo_code_id() {
        return promo_code_id;
    }

    public void setPromo_code_id(String promo_code_id) {
        this.promo_code_id = promo_code_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}



