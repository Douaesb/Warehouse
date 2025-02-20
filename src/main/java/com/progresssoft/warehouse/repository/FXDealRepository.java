package com.progresssoft.warehouse.repository;


import com.progresssoft.warehouse.entity.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FXDealRepository extends JpaRepository<FXDeal, UUID> {
    Optional<FXDeal> findByDealUniqueId(UUID dealUniqueId);
}
