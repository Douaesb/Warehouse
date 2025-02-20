package com.progresssoft.warehouse.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FXDealDTO {

    @NotNull(message = "Deal Unique ID is required")
    private UUID dealUniqueId;

    @NotBlank(message = "From Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid From Currency Code (ISO 4217)")
    private String fromCurrency;

    @NotBlank(message = "To Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid To Currency Code (ISO 4217)")
    private String toCurrency;

    @NotNull(message = "Deal Timestamp is required")
    @PastOrPresent(message = "Deal Timestamp must not be in the future")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal Amount is required")
    @Positive(message = "Deal Amount must be positive")
    @Digits(integer = 15, fraction = 2, message = "Deal Amount must be a valid monetary value with up to 2 decimal places")
    private BigDecimal dealAmount;
}
