package com.example.bitmonsters.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "promo_code_list")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class PromoCode implements Serializable{
    @Id
    private String promo_code_id;

    @NotBlank
    private Long retailer_id;

    @NotBlank
    private String promo_code_desc;

    @NotBlank
    private Date start_date;

    @NotBlank
    private Date end_date;

    @NotBlank
    private String category;

    public String getPromo_code_id() {
        return promo_code_id;
    }

    public void setPromo_code_id(String promo_code_id) {
        this.promo_code_id = promo_code_id;
    }

    public Long getRetailer_id() {
        return retailer_id;
    }

    public void setRetailer_id(Long retailer_id) {
        this.retailer_id = retailer_id;
    }

    public String getPromo_code_desc() {
        return promo_code_desc;
    }

    public void setPromo_code_desc(String promo_code_desc) {
        this.promo_code_desc = promo_code_desc;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}



