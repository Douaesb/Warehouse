package com.progresssoft.warehouse.dto;

import lombok.*;


@Getter
@AllArgsConstructor
@Builder
public final class FXDealResponseDTO {
    private final boolean success;
    private final String message;
    private final FXDealDTO fxDeal;
}
