package com.progresssoft.warehouse.controller;

import com.progresssoft.warehouse.dto.FXDealDTO;
import com.progresssoft.warehouse.dto.FXDealResponseDTO;
import com.progresssoft.warehouse.service.FXDealService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deals")
public class FXDealController {

    private final FXDealService fxDealService;

    public FXDealController(FXDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping
    public ResponseEntity<FXDealResponseDTO> saveDeal(@Valid @RequestBody FXDealDTO fxDealDTO) {
        FXDealResponseDTO savedDeal = fxDealService.saveFXDeal(fxDealDTO);
        return ResponseEntity.ok(savedDeal);
    }
}