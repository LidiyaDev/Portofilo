package com.dashenbank.mttodb.Repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dashenbank.mttodb.Entity.FetchedData;

public interface FetchedDataRepository extends JpaRepository<FetchedData, BigDecimal> {
    
}
