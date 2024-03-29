package org.example.network_models.request;

import org.example.utility.Commands;

public class FilterGreaterThanDiscountRequest  extends Request {
    public final int discount;

    public FilterGreaterThanDiscountRequest(int discount) {
        super(Commands.COUNT_BY_DISCOUNT);
        this.discount = discount;
    }
}
