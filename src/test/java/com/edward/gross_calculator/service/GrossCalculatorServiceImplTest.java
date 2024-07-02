package com.edward.gross_calculator.service;

import com.edward.gross_calculator.dto.EmployeeSlipDto;
import com.edward.gross_calculator.request.NetIncomeRequest;
import com.edward.gross_calculator.service.impl.GrossCalculatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GrossCalculatorServiceImplTest {

    @Mock
    private TaxService taxService;

    @InjectMocks
    private GrossCalculatorServiceImpl grossCalculatorService;

    @Test
    void testCalculate() {
        when(taxService.calculateTaxableIncome(anyDouble())).thenReturn(100.00);
        when(taxService.calculateBasic(anyDouble())).thenReturn(120.00);
        when(taxService.calculateEmployerCost(anyDouble())).thenReturn(10.0);
        when(taxService.calculateEmployeeCost(anyDouble())).thenReturn(5.0);

        var expected = EmployeeSlipDto.builder()
                .basic(120.0)
                .gross(130.0)
                .tax(20.0)
                .employeePension(5.0)
                .employerPension(10.0)
                .build();

        var request = NetIncomeRequest.builder()
                .totalAllowance(10.0)
                .net(80.0)
                .build();
        var actual = grossCalculatorService.calculate(request);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
