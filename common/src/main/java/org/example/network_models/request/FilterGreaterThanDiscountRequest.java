package org.example.network_models.request;

import org.example.utility.Commands;

public class FilterGreaterThanDiscountRequest  extends Request {
    public final int discount;

    public FilterGreaterThanDiscountRequest(int discount) {
        super(Commands.FILTER_GREATER_THAN_DISCOUNT);
        this.discount = discount;
    }
}
