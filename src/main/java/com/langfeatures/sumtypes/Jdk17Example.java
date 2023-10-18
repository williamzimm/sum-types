package com.langfeatures.sumtypes;

import java.math.BigDecimal;

public class Jdk17Example {

    private sealed interface Security {

        record ExchangeListed(String tickerSymbol, Exchange primaryExchange) implements Security {}

        record MutualFund(Cusip cusip) implements Security {}
    }

    public BigDecimal getPrice(Security security) {
        if (security instanceof Security.ExchangeListed exchangeListed) {

            return IntradayPriceRetriever.retrieveIntradayPrice(
                    exchangeListed.tickerSymbol(),
                    exchangeListed.primaryExchange()
            );
        } else if (security instanceof Security.MutualFund mutualFund) {

            return MutualFundPriceRetriever.retrieveClosingPrice(mutualFund.cusip());
        }

        throw new IllegalStateException("Unsupported security: " + security.getClass());
    }
}