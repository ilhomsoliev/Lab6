package org.example.network_models.response;

import org.example.utility.Commands;

public class CountByDiscountResponse extends Response {
    public CountByDiscountResponse(String error) {
        super(Commands.CLEAR, error);
    }
}

