package com.langfeatures.sumtypes.calculationexample;

import java.math.BigDecimal;
import java.util.Optional;

public class CalculationResult {
    
    private final String errorMessage;
    private final BigDecimal calculation;

    private CalculationResult(String errorMessage, BigDecimal calculation) {
        this.errorMessage = errorMessage;
        this.calculation = calculation;
    }

    public static CalculationResult fromError(String errorMessage) {
        return new CalculationResult(errorMessage, null);
    }

    public static CalculationResult fromSuccess(BigDecimal calculation) {
        return new CalculationResult(null, calculation);
    }

    public boolean isSuccess() {
        return Optional.ofNullable(errorMessage).isEmpty();
    }

    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }

    public Optional<BigDecimal> getCalculation() {
        return Optional.ofNullable(calculation);
    }
}
