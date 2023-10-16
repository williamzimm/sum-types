package com.langfeatures.sumtypes;

import java.math.BigDecimal;

public class Jdk17Example {

    sealed interface IndividualTrade {

        record AmountBased(BigDecimal amountRequested) implements IndividualTrade {}

        record ShareBased(BigDecimal sharesRequested, BigDecimal executionPrice) implements IndividualTrade {}
    }

    public static BigDecimal calculateNotionalValue(IndividualTrade individualTrade) {
        if (individualTrade instanceof IndividualTrade.AmountBased amountBased) {
            return amountBased.amountRequested;
        }
        else if (individualTrade instanceof IndividualTrade.ShareBased shareBased) {
            return shareBased.sharesRequested.multiply(shareBased.executionPrice);
        }
        throw new IllegalStateException("Can't handle IndividualTrade " + individualTrade.getClass());
    }
}