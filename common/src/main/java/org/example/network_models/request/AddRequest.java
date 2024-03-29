package org.example.network_models.request;


import org.example.model.Ticket;
import org.example.utility.Commands;

public class AddRequest extends Request {
    public final Ticket ticket;
    public AddRequest(Ticket ticket) {
        super(Commands.ADD);
        this.ticket = ticket;
    }
}
