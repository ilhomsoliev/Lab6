package org.example.network_models.response;


import org.example.model.Ticket;
import org.example.utility.Commands;

import java.util.List;

public class ShowResponse extends Response {
    public final List<Ticket> products;

    public ShowResponse(List<Ticket> products, String error) {
        super(Commands.SHOW, error);
        this.products = products;
    }
}
