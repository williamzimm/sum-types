package com.langfeatures.sumtypes;

import java.math.BigDecimal;

public class Jdk21Example {

    private sealed interface Security {

        record ExchangeListed(String tickerSymbol, Exchange primaryExchange) implements Security {}

        record MutualFund(Cusip cusip) implements Security {}

    }

    public BigDecimal getPrice(Security security) {
        return switch (security) {
            case Security.ExchangeListed exchangeListed ->
                    IntradayPriceRetriever.retrieveIntradayPrice(exchangeListed.tickerSymbol(), exchangeListed.primaryExchange());
            case Security.MutualFund mutualFund ->
                    MutualFundPriceRetriever.retrieveClosingPrice(mutualFund.cusip());
        };
    }

    public BigDecimal getPrice_withRecordPatterns(Security security) {
        return switch (security) {
            case Security.ExchangeListed(var symbol, var exchange) ->
                    IntradayPriceRetriever.retrieveIntradayPrice(symbol, exchange);
            case Security.MutualFund(var cusip) ->
                    MutualFundPriceRetriever.retrieveClosingPrice(cusip);
        };
    }
}