package org.example.network_models.request;


import org.example.utility.Commands;

public class HelpRequest extends Request {
  public HelpRequest() {
    super(Commands.HELP);
  }
}
