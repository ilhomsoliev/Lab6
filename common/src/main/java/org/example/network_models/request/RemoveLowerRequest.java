package org.example.network_models.request;

import org.example.model.Ticket;
import org.example.utility.Commands;

public class RemoveLowerRequest extends Request {
    public final Ticket ticket;

    public RemoveLowerRequest(Ticket ticket) {
        super(Commands.REMOVE_BY_ID);
        this.ticket = ticket;
    }
}

