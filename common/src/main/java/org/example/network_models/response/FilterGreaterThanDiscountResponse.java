package org.example.network_models.response;

import org.example.utility.Commands;

public class FilterGreaterThanDiscountResponse extends Response {
    public FilterGreaterThanDiscountResponse(String error, String result) {
        super(Commands.FILTER_GREATER_THAN_DISCOUNT, error, result);
    }
}
