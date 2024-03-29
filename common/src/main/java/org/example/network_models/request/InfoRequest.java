package org.example.network_models.request;


import org.example.utility.Commands;

public class InfoRequest extends Request {
  public InfoRequest() {
    super(Commands.INFO);
  }
}
