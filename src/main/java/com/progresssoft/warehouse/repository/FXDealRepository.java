package com.progresssoft.warehouse.repository;


import com.progresssoft.warehouse.entity.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FXDealRepository extends JpaRepository<FXDeal, UUID> {
    Optional<FXDeal> findByDealUniqueId(UUID dealUniqueId);
}
