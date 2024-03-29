package org.example.network_models.response;

import org.example.utility.Commands;

public class ClearResponse extends Response {
  public ClearResponse(String error) {
    super(Commands.CLEAR, error, "");
  }
}
