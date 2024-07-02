package com.edward.gross_calculator.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetIncomeRequest {

    @NotNull(message = "net is required")
    @Min(value = 1, message = "net should be at least 1")
    private Double net;

    @NotNull
    @Min(value = 0)
    private Double totalAllowance;
}
