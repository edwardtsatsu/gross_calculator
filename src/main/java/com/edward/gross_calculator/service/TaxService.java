package com.edward.gross_calculator.service;

public interface TaxService {

    Double calculateTaxableIncome(Double net);

    Double calculateBasic(Double taxableIncome);

    Double calculateEmployeeCost(Double total);

    Double calculateEmployerCost(Double total);
}
