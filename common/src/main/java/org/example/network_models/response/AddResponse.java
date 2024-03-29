package org.example.network_models.response;

import org.example.utility.Commands;

public class AddResponse extends Response {
  public final int newId;

  public AddResponse(int newId, String error) {
    super(Commands.ADD, error);
    this.newId = newId;
  }
}
