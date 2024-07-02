package com.edward.gross_calculator.service.impl;

import com.edward.gross_calculator.dto.EmployeeSlipDto;
import com.edward.gross_calculator.request.NetIncomeRequest;
import com.edward.gross_calculator.service.GrossCalculatorService;
import com.edward.gross_calculator.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrossCalculatorServiceImpl implements GrossCalculatorService {

    private final TaxService taxService;

    @Override
    public EmployeeSlipDto calculate(NetIncomeRequest netIncomeRequest) {
        Double taxableIncome = taxService.calculateTaxableIncome(netIncomeRequest.getNet());
        Double tax = taxableIncome - netIncomeRequest.getNet();
        Double basic = taxService.calculateBasic(taxableIncome);
        Double gross = basic + netIncomeRequest.getTotalAllowance();
        Double employerCost = taxService.calculateEmployerCost(gross);
        Double employeeCost = taxService.calculateEmployeeCost(gross);

        return EmployeeSlipDto.builder()
                .basic(basic)
                .gross(gross)
                .tax(tax)
                .employeePension(employeeCost)
                .employerPension(employerCost)
                .build();
    }
}
