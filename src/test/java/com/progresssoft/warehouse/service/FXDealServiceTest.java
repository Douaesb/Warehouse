package com.progresssoft.warehouse.service;

import com.progresssoft.warehouse.dto.FXDealDTO;
import com.progresssoft.warehouse.dto.FXDealResponseDTO;
import com.progresssoft.warehouse.entity.FXDeal;
import com.progresssoft.warehouse.exception.DuplicateDealException;
import com.progresssoft.warehouse.exception.InvalidDealException;
import com.progresssoft.warehouse.mapper.FXDealMapper;
import com.progresssoft.warehouse.repository.FXDealRepository;
import com.progresssoft.warehouse.service.impl.FXDealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FXDealServiceTest {

    @Mock
    private FXDealRepository fxDealRepository;

    @Mock
    private FXDealMapper fxDealMapper;

    @InjectMocks
    private FXDealServiceImpl fxDealService;

    private FXDealDTO validDealDTO;
    private FXDeal fxDealEntity;

    @BeforeEach
    void setUp() {
        UUID dealId = UUID.randomUUID();

        validDealDTO = FXDealDTO.builder()
                .dealUniqueId(dealId)
                .fromCurrency("USD")
                .toCurrency("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(new BigDecimal("1000.20"))
                .build();

        fxDealEntity = FXDeal.builder()
                .dealUniqueId(dealId)
                .fromCurrency("USD")
                .toCurrency("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(new BigDecimal("1000.50"))
                .build();
    }

    @Test
    void saveFXDeal_ShouldSaveSuccessfully_WhenDealIsValid() {
        when(fxDealRepository.findByDealUniqueId(validDealDTO.getDealUniqueId())).thenReturn(Optional.empty());
        when(fxDealMapper.toEntity(validDealDTO)).thenReturn(fxDealEntity);
        when(fxDealRepository.save(any(FXDeal.class))).thenReturn(fxDealEntity);
        when(fxDealMapper.toDTO(any(FXDeal.class))).thenReturn(validDealDTO);

        FXDealResponseDTO response = fxDealService.saveFXDeal(validDealDTO);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat("FX Deal saved successfully.").isEqualTo(response.getMessage());
        assertThat(response.getFxDeal()).isNotNull();
        assertEquals(validDealDTO.getDealUniqueId(), response.getFxDeal().getDealUniqueId());

        verify(fxDealRepository, times(1)).existsById(validDealDTO.getDealUniqueId());
        verify(fxDealRepository, times(1)).save(any(FXDeal.class));
        verify(fxDealMapper, times(1)).toEntity(validDealDTO);
        verify(fxDealMapper, times(1)).toDTO(any(FXDeal.class));
    }

    @Test
    void saveFXDeal_ShouldThrowDuplicateDealException_WhenDealAlreadyExists() {
        when(fxDealRepository.existsById(validDealDTO.getDealUniqueId())).thenReturn(true);

        assertThatExceptionOfType(DuplicateDealException.class)
                .isThrownBy(() -> fxDealService.saveFXDeal(validDealDTO))
                .withMessage("Duplicate Deal: A deal with ID " + validDealDTO.getDealUniqueId() + " already exists.");

        verify(fxDealRepository, times(1)).existsById(validDealDTO.getDealUniqueId());
        verify(fxDealRepository, never()).save(any(FXDeal.class));
    }

    @Test
    void saveFXDeal_ShouldThrowInvalidDealException_WhenCurrenciesAreTheSame() {
        String currency = "USD";
        FXDealDTO invalidFXDeal = FXDealDTO.builder()
                .dealUniqueId(UUID.randomUUID())
                .fromCurrency(currency)
                .toCurrency(currency)
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(new BigDecimal("1000.20"))
                .build();

        assertThatExceptionOfType(InvalidDealException.class)
                .isThrownBy(() -> fxDealService.saveFXDeal(invalidFXDeal))
                .withMessage("From and To currencies must be different.");

        verify(fxDealRepository, never()).save(any(FXDeal.class));
    }
}
