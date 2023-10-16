package com.langfeatures.sumtypes.calculationexample;

public class CalculationConsumer {
    
    public String generateWebResponse(CalculationResult result) {

        if (result.isSuccess()) {
            return String.format(
                """
                    {
                      "calculation" : %s
                      "error_message" : ""
                    }
                    """,
                result.getCalculation().orElseThrow()
            );
        }

        return String.format(
            """
                {
                  "calculation" :
                  "error_message" : %s
                }
                """,
            result.getErrorMessage()
        );
    }

}
