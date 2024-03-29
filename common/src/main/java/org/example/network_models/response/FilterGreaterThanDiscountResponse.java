package org.example.network_models.response;

import org.example.utility.Commands;

public class FilterGreaterThanDiscountResponse  extends Response {
    public FilterGreaterThanDiscountResponse(String error) {
        super(Commands.REMOVE_BY_ID, error);
    }
}
