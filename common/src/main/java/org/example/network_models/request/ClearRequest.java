package org.example.network_models.request;

import org.example.utility.Commands;

public class ClearRequest extends Request {
  public ClearRequest() {
    super(Commands.CLEAR);
  }
}
