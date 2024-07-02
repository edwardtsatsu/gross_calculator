package com.edward.gross_calculator;

import com.edward.gross_calculator.dto.EmployeeSlipDto;
import com.edward.gross_calculator.request.NetIncomeRequest;
import com.edward.gross_calculator.service.impl.GrossCalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrossCalculatorApplication implements ApplicationRunner {

	@Autowired
	private GrossCalculatorServiceImpl grossCalculatorService;

	public static void main(String[] args) {
		SpringApplication.run(GrossCalculatorApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		var request = NetIncomeRequest.builder()
				.totalAllowance(0.0)
				.net(17672.83)
				.build();
		EmployeeSlipDto slipDto = grossCalculatorService.calculate(request);
		System.out.println("slip "+slipDto);
	}
}

