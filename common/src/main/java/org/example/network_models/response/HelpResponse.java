package org.example.network_models.response;


import org.example.utility.Commands;

public class HelpResponse extends Response {
    public final String helpMessage;

    public HelpResponse(String helpMessage, String error) {
        super(Commands.HELP, error, "");
        this.helpMessage = helpMessage;
    }
}
