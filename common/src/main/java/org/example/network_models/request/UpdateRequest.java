package org.example.network_models.request;


import org.example.model.Ticket;
import org.example.utility.Commands;

public class UpdateRequest extends Request {
  public final int id;
  public final Ticket updatedTicket;

  public UpdateRequest(int id, Ticket updatedTicket) {
    super(Commands.UPDATE);
    this.id = id;
    this.updatedTicket = updatedTicket;
  }
}
