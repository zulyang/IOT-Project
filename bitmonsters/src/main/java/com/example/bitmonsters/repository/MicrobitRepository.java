package com.example.bitmonsters.repository;

import com.example.bitmonsters.model.Microbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicrobitRepository extends JpaRepository<Microbit, Long> {

}



