package com.example.bitmonsters.repository;

import com.example.bitmonsters.model.PromoCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface PromoCodeListRepository extends JpaRepository<PromoCode, String> {

     @Query("SELECT p FROM PromoCode p WHERE p.promo_code_id = :promo_code_id")
     PromoCode findByPromoCodeId(@Param("promo_code_id") String promo_code_id);

}



