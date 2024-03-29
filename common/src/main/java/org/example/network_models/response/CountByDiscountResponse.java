package org.example.network_models.response;

import org.example.utility.Commands;

public class CountByDiscountResponse extends Response {
    public CountByDiscountResponse(String error, String result) {
        super(Commands.COUNT_BY_DISCOUNT, error, result);
    }
}

