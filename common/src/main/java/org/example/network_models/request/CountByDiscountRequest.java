package org.example.network_models.request;

import org.example.utility.Commands;

public class CountByDiscountRequest  extends Request {
    public final int discount;

    public CountByDiscountRequest(int discount) {
        super(Commands.COUNT_BY_DISCOUNT);
        this.discount = discount;
    }
}
