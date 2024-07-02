package com.edward.gross_calculator.service;

import com.edward.gross_calculator.dto.EmployeeSlipDto;
import com.edward.gross_calculator.request.NetIncomeRequest;

public interface GrossCalculatorService {

    EmployeeSlipDto calculate(NetIncomeRequest netIncomeRequest);
}
