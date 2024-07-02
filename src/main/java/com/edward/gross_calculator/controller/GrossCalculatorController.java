package com.edward.gross_calculator.controller;

import com.edward.gross_calculator.dto.EmployeeSlipDto;
import com.edward.gross_calculator.request.NetIncomeRequest;
import com.edward.gross_calculator.service.GrossCalculatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GrossCalculatorController {

    private final GrossCalculatorService grossCalculatorService;

    @PostMapping("payslip")
    ResponseEntity<EmployeeSlipDto> calculateSlip(@RequestBody @Valid NetIncomeRequest netIncomeRequest) {
        EmployeeSlipDto dto = grossCalculatorService.calculate(netIncomeRequest);
        return ResponseEntity.ok(dto);
    }
}
