package com.langfeatures.sumtypes;

import java.math.BigDecimal;

public class Jdk11Example {

    private interface Security {

        class ExchangeListed implements Security{
            private final String tickerSymbol;
            private final Exchange primaryExchange;

            public ExchangeListed(String tickerSymbol, Exchange primaryExchange) {
                this.tickerSymbol = tickerSymbol;
                this.primaryExchange = primaryExchange;
            }

            public String getTickerSymbol() {
                return tickerSymbol;
            }

            public Exchange getPrimaryExchange() {
                return primaryExchange;
            }
        }

        class MutualFund implements Security {

            private final Cusip cusip;

            public MutualFund(Cusip cusip) {
                this.cusip = cusip;
            }

            public Cusip getCusip() {
                return cusip;
            }
        }
    }

    public BigDecimal getPrice(Security security) {
        if (security instanceof Security.ExchangeListed) {
            Security.ExchangeListed exchangeListed = (Security.ExchangeListed) security;

            return IntradayPriceRetriever.retrieveIntradayPrice(
                    exchangeListed.getTickerSymbol(),
                    exchangeListed.getPrimaryExchange()
            );
        } else if (security instanceof Security.MutualFund) {
            Security.MutualFund mutualFund = (Security.MutualFund) security;

            return MutualFundPriceRetriever.retrieveClosingPrice(mutualFund.getCusip());
        }

        throw new IllegalStateException("Unsupported security: " + security.getClass());
    }
}