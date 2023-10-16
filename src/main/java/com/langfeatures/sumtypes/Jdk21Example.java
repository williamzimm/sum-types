package com.langfeatures.sumtypes;

import java.math.BigDecimal;

public class Jdk21Example {

    sealed interface IndividualTrade {

        record AmountBased(BigDecimal amountRequested) implements IndividualTrade {}

        record ShareBased(BigDecimal sharesRequested, BigDecimal executionPrice) implements IndividualTrade {}
    }

    public static BigDecimal calculateNotionalValue(IndividualTrade individualTrade) {
        return switch (individualTrade) {
            case IndividualTrade.ShareBased shareBased ->
                shareBased.sharesRequested().multiply(shareBased.executionPrice());
            case IndividualTrade.AmountBased amountBased -> amountBased.amountRequested();
        };
    }

    public static BigDecimal calculateNotionalValue_withRecordPatterns(IndividualTrade individualTrade) {
        return switch (individualTrade) {
            case IndividualTrade.ShareBased(var sharesRequested, var executionPrice) ->
                sharesRequested.multiply(executionPrice);
            case IndividualTrade.AmountBased(var amountReequested) -> amountReequested;
        };
    }

}