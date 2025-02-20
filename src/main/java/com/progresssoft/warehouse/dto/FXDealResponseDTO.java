package com.progresssoft.warehouse.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FXDealResponseDTO {
    private boolean success;
    private String message;
    private FXDealDTO fxDeal;
}
