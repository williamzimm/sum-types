package com.langfeatures.sumtypes;

import java.math.BigDecimal;

public class Jdk11Example {

    interface IndividualTrade {

        class AmountBased implements IndividualTrade {
            private final BigDecimal amountRequested;

            public AmountBased(BigDecimal amountRequested) {
                this.amountRequested = amountRequested;
            }
        }

        class ShareBased implements IndividualTrade {

            private final BigDecimal sharesRequested;
            private final BigDecimal executionPrice;

            public ShareBased(BigDecimal sharesRequested,
                              BigDecimal executionPrice) {
                this.sharesRequested = sharesRequested;
                this.executionPrice = executionPrice;
            }
        }
    }

    public static BigDecimal calculateNotionalValue(IndividualTrade individualTrade) {
        if (individualTrade instanceof IndividualTrade.AmountBased) {
            IndividualTrade.AmountBased amountBased = (IndividualTrade.AmountBased) individualTrade;
            return amountBased.amountRequested;
        }
        else if (individualTrade instanceof IndividualTrade.ShareBased) {
            IndividualTrade.ShareBased shareBased = (IndividualTrade.ShareBased) individualTrade;
            return shareBased.sharesRequested.multiply(shareBased.executionPrice);
        }
        throw new IllegalStateException("Can't handle IndividualTrade " + individualTrade.getClass());
    }
}