package org.example.network_models.response;


import org.example.model.Ticket;
import org.example.utility.Commands;

import java.util.List;

public class ShowResponse extends Response {
    public final List<Ticket> productsList;

    public ShowResponse(String error, List<Ticket> productsList) {
        super(Commands.SHOW, error, "");
        this.productsList = productsList;
    }
}
