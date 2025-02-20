package com.progresssoft.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fx_deals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FXDeal {

    @Id
    private UUID dealUniqueId;

    private String fromCurrency;

    private String toCurrency;

    private LocalDateTime dealTimestamp;

    private BigDecimal dealAmount;
}