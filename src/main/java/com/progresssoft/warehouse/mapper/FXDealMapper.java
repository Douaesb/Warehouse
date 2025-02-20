package com.progresssoft.warehouse.mapper;

import com.progresssoft.warehouse.dto.FXDealDTO;
import com.progresssoft.warehouse.entity.FXDeal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FXDealMapper {

    FXDeal toEntity(FXDealDTO fxDealDTO);
    FXDealDTO toDTO(FXDeal fxDeal);
}
