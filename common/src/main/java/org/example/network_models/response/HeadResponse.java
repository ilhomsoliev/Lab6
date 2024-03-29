package org.example.network_models.response;


import org.example.model.Ticket;
import org.example.utility.Commands;

public class HeadResponse extends Response {
  public final Ticket ticket;

  public HeadResponse(Ticket ticket, String error) {
    super(Commands.HEAD, error, "");
    this.ticket = ticket;
  }
}
