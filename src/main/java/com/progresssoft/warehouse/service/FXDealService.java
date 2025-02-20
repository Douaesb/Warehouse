package com.progresssoft.warehouse.service;

import com.progresssoft.warehouse.dto.FXDealDTO;
import com.progresssoft.warehouse.dto.FXDealResponseDTO;

public interface FXDealService {
    FXDealResponseDTO saveFXDeal(FXDealDTO fxDealDTO);

}
