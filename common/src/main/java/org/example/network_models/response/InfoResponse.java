package org.example.network_models.response;


import org.example.utility.Commands;

public class InfoResponse extends Response {
  public final String infoMessage;

  public InfoResponse(String infoMessage, String error) {
    super(Commands.INFO, error);
    this.infoMessage = infoMessage;
  }
}
