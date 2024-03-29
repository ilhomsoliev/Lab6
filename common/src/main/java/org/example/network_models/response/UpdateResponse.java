package org.example.network_models.response;


import org.example.utility.Commands;

public class UpdateResponse extends Response {
  public UpdateResponse(String error) {
    super(Commands.UPDATE, error, "");
  }
}
