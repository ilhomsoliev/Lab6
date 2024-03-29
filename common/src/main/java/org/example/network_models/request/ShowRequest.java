package org.example.network_models.request;


import org.example.utility.Commands;

public class ShowRequest extends Request {
  public ShowRequest() {
    super(Commands.SHOW);
  }
}
