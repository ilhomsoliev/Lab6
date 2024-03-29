package org.example.network_models.request;


import org.example.utility.Commands;

public class HeadRequest extends Request {
  public HeadRequest() {
    super(Commands.HEAD);
  }
}
