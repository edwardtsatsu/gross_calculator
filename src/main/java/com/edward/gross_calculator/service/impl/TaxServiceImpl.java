package com.edward.gross_calculator.service.impl;

import com.edward.gross_calculator.service.TaxService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TaxServiceImpl implements TaxService {

    private final LinkedHashMap<Double, Double> rates;
    private final Double TAX_FREE_VALUE = 490.0;
    private final Double TIER_1 = 13.0 / 100;
    private final Double TIER_2 = 5.5 / 100;
    private final Double TIER_3 = 5.0 / 100;

    public TaxServiceImpl() {
        rates = new LinkedHashMap<>();
        rates.put(110.0, 5.0);
        rates.put(130.0, 10.0);
        rates.put(3166.67, 17.5);
        rates.put(16000.0, 25.0);
        rates.put(30520.0, 30.0);
        rates.put(50000.0, 35.0);
    }

    @Override
    public Double calculateTaxableIncome(Double net) {
        if (net < TAX_FREE_VALUE) {
            return net;
        }
        Double rate = rates.entrySet()
                .stream()
                .filter(x -> x.getKey() > net)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(0.0);

        var lowerRates = rates.entrySet()
                .stream()
                .filter(x -> x.getValue() < rate)
                .toList();

        Double dd = lowerRates
                .stream()
                .map(x -> (x.getKey() - (x.getValue()/100) * x.getKey()))
                .reduce(0.0, Double::sum);
        Double netInBracket = net - (TAX_FREE_VALUE + dd);

        Double rateInBracket = 1.0 - (rate/100);
        Double x = netInBracket / rateInBracket;

        Double partialGross = lowerRates.stream().map(Map.Entry::getKey).reduce(0.0, Double::sum);

        return partialGross + TAX_FREE_VALUE + x;
    }

    @Override
    public Double calculateBasic(Double taxableIncome) {
        Double totalEmployeeCost = 1 - (TIER_2 + TIER_3);
        return taxableIncome / totalEmployeeCost;
    }

    @Override
    public Double calculateEmployeeCost(Double total) {
        return (TIER_2 + TIER_3) * total;
    }

    @Override
    public Double calculateEmployerCost(Double total) {
        return (TIER_1 + TIER_3) * total;
    }
}
