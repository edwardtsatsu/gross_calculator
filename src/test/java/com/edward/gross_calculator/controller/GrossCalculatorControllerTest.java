package com.edward.gross_calculator.controller;

import com.edward.gross_calculator.dto.EmployeeSlipDto;
import com.edward.gross_calculator.request.NetIncomeRequest;
import com.edward.gross_calculator.service.GrossCalculatorService;
import com.edward.gross_calculator.service.impl.GrossCalculatorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GrossCalculatorController.class)
@ExtendWith(SpringExtension.class)
public class GrossCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GrossCalculatorServiceImpl grossCalculatorService;

    @Test
    void testCalculateSlip() throws Exception {
        var request = NetIncomeRequest.builder()
                .net(100.0)
                .totalAllowance(0.0)
                .build();
        var employeeSlipDto = EmployeeSlipDto.builder().build();
        when(grossCalculatorService.calculate(request)).thenReturn(employeeSlipDto);
        mockMvc.perform(post("/api/v1/payslip")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testCalculateSlipShouldReturnBadRequest() throws Exception {
        var request = NetIncomeRequest.builder()
                .net(null)
                .totalAllowance(0.0)
                .build();
        var employeeSlipDto = EmployeeSlipDto.builder().build();
        when(grossCalculatorService.calculate(request)).thenReturn(employeeSlipDto);
        mockMvc.perform(post("/api/v1/payslip")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }


}
