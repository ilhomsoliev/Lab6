package org.example.network_models.response;


import org.example.model.Ticket;
import org.example.utility.Commands;

import java.util.List;

public class ShowResponse extends Response {
    public final String products;

    public ShowResponse(String products, String error) {
        super(Commands.SHOW, error, products);
        this.products = products;
    }
}
