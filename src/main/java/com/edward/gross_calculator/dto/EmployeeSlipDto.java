package com.edward.gross_calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeSlipDto {
    private Double basic;
    private Double gross;
    private Double tax;
    private Double employeePension;
    private Double employerPension;
}
