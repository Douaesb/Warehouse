package com.progresssoft.warehouse.service.impl;

import com.progresssoft.warehouse.dto.FXDealDTO;
import com.progresssoft.warehouse.entity.FXDeal;
import com.progresssoft.warehouse.mapper.FXDealMapper;
import com.progresssoft.warehouse.repository.FXDealRepository;
import com.progresssoft.warehouse.service.FXDealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class FXDealServiceImpl implements FXDealService {
    private final FXDealRepository fxDealRepository;
    private final FXDealMapper fxDealMapper;

    public FXDealServiceImpl(FXDealRepository fxDealRepository, FXDealMapper fxDealMapper) {
        this.fxDealRepository = fxDealRepository;
        this.fxDealMapper = fxDealMapper;
    }

    @Transactional
    public FXDealDTO saveFXDeal(FXDealDTO fxDealDTO) {
        UUID dealId = fxDealDTO.getDealUniqueId();

        Optional<FXDeal> existingDeal = fxDealRepository.findByDealUniqueId(dealId);
        if (existingDeal.isPresent()) {
            throw new IllegalArgumentException("Duplicate Deal: A deal with ID " + dealId + " already exists.");
        }

        FXDeal fxDeal = fxDealMapper.toEntity(fxDealDTO);
        FXDeal savedDeal = fxDealRepository.save(fxDeal);
        return fxDealMapper.toDTO(savedDeal);
    }
}
