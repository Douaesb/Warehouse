package com.progresssoft.warehouse.service.impl;

import com.progresssoft.warehouse.dto.FXDealDTO;
import com.progresssoft.warehouse.dto.FXDealResponseDTO;
import com.progresssoft.warehouse.entity.FXDeal;
import com.progresssoft.warehouse.exception.DuplicateDealException;
import com.progresssoft.warehouse.exception.InvalidDealException;
import com.progresssoft.warehouse.mapper.FXDealMapper;
import com.progresssoft.warehouse.repository.FXDealRepository;
import com.progresssoft.warehouse.service.FXDealService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


    public FXDealResponseDTO saveFXDeal(@Valid FXDealDTO fxDealDTO) {
        UUID dealId = fxDealDTO.getDealUniqueId();
        log.info("Processing FX deal | Deal ID: {}", dealId);

        validateFXDeal(fxDealDTO);

        FXDeal fxDeal = fxDealMapper.toEntity(fxDealDTO);
        FXDeal savedDeal = fxDealRepository.save(fxDeal);
        log.info("FX Deal saved successfully | Deal ID: {}", dealId);

        return FXDealResponseDTO.builder()
                .success(true)
                .message("FX Deal saved successfully.")
                .fxDeal(fxDealMapper.toDTO(savedDeal))
                .build();
    }

    private void validateFXDeal(FXDealDTO fxDealDTO) {
        if (fxDealRepository.existsById(fxDealDTO.getDealUniqueId())) {
            throw new DuplicateDealException(fxDealDTO.getDealUniqueId());
        }

        if (fxDealDTO.getFromCurrency().equals(fxDealDTO.getToCurrency())) {
            throw new InvalidDealException("From and To currencies must be different.");
        }
    }
}
